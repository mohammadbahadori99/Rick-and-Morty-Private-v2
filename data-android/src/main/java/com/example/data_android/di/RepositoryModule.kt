package com.example.data_android.di

import com.example.data_android.CharactersRepositoryImpl
import com.example.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCharactersRepository(charactersRepositoryImpl: CharactersRepositoryImpl): CharactersRepository
}