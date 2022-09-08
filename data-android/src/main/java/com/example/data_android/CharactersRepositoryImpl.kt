package com.example.data_android

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.data_android.local.CharacterDao
import com.example.data_android.model.toCharacterDomainModel
import com.example.data_android.remote.MyRemoteMediator
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val dao: CharacterDao,
    private val myRemoteMediator: MyRemoteMediator,
) : CharactersRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchCharacters(pageSize: Int) = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = myRemoteMediator
    ) {
        val myQuery =
            SimpleSQLiteQuery("SELECT * FROM characters  ORDER BY time asc ")
        dao.getAllCharacters()
    }.flow.mapLatest { pagingData ->
        pagingData.map { entity ->
            entity.toCharacterDomainModel()
        }
    }
}