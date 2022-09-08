package com.example.data_android.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data_android.CharactersDataBase
import com.example.data_android.local.CharacterDao
import com.example.data_android.model.MyResponseEntity
import com.example.data_android.model.toMyResponseDTO
import com.example.data_android.model.toMyResponseEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MyRemoteMediator @Inject constructor(
    private val database: CharactersDataBase,
    private val characterDao: CharacterDao,
    private val networkService: WebService
) : RemoteMediator<Int, MyResponseEntity>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MyResponseEntity>
    ): MediatorResult {
        try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    null
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val remoteKey = ((characterDao.getRecordsCount().size) / 20) + 1
                    if ((characterDao.getRecordsCount().size) % 20 != 0) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey
                }
            }
            val data = networkService.fetchCharacters(
                page = loadKey ?: 1,
            )
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.clearAll()
                }
                data.let { list ->
                    characterDao.insertAllCharacters(
                        list.results.map {
                            it.toMyResponseDTO().toMyResponseEntity()
                        }
                    )
                }
            }
            return MediatorResult.Success(endOfPaginationReached = data.results.size < 20)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}