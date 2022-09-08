package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.base.UseCase
import com.example.domain.model.CharacterDomainModel
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) :
    UseCase<Int, Flow<PagingData<CharacterDomainModel>>>() {
    override fun execute(parameters: Int): Flow<PagingData<CharacterDomainModel>> {
        return charactersRepository.fetchCharacters(parameters)
    }
}