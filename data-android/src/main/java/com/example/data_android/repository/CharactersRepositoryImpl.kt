package com.example.data_android.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
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
        dao.getAllCharacters()
    }.flow.mapLatest { pagingData ->
        pagingData.map { entity ->
            entity.toCharacterDomainModel()
        }
    }
}