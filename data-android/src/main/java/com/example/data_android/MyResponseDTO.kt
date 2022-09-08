package com.example.data_android


data class MyResponseDTO(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val locationName: String,
    val locationUrl: String,
    val name: String,
    val originName: String,
    val originUrl: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)