package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.CharacterDomainModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
     fun fetchCharacters(pageSize:Int): Flow<PagingData<CharacterDomainModel>>
}