package com.example.rickandmorty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.usecase.FetchCharactersUseCase
import com.example.rickandmorty.model.CharacterView
import com.example.rickandmorty.model.toCharacterView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(
    fetchCharactersUseCase: FetchCharactersUseCase,
) : ViewModel() {

    private var _myList: Flow<PagingData<CharacterView>> = emptyFlow()
    val myList: Flow<PagingData<CharacterView>> get() = _myList

    init {
        _myList = fetchCharactersUseCase(1).cachedIn(viewModelScope)
            .map { pagingData -> pagingData.map { it.toCharacterView() } }
    }
}