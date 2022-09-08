package com.example.data_android.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "characters")
@Serializable
data class MyResponseEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    @Embedded(prefix = "location_")
    val location: Location,
    val name: String,
    @Embedded(prefix = "origin_")
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    val time: Long
)