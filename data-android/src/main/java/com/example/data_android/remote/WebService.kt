package com.example.data_android.remote


import com.example.data_android.model.ServerResponseModel
import retrofit2.http.Query

interface WebService {
    @retrofit2.http.GET("/api/character/")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
    ): ServerResponseModel
}