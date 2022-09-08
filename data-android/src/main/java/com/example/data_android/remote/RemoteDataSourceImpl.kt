package com.example.data_android.remote

//class RemoteDataSourceImpl @Inject constructor(private val webService: WebService) {
//    suspend fun fetchCharacters(): MyResponse<List<MyResponseDTO>> {
//        return try {
//            val data = webService.fetchCharacters(2)
//            MyResponse.Success(data.results.toMyResponseDTO())
//        } catch (e: Exception) {
//            MyResponse.Error(e)
//        }
//    }
//}