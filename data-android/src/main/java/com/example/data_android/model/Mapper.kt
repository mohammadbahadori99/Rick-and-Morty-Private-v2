package com.example.data_android.model

import com.example.domain.model.CharacterDomainModel

fun Result.toMyResponseEntity() = MyResponseEntity(
    this.created,
    this.episode,
    this.gender,
    this.id,
    this.image,
    this.location,
    this.name,
    this.origin,
    this.species,
    this.status,
    this.type,
    this.url
)
fun MyResponseEntity.toCharacterDomainModel() = CharacterDomainModel(
    this.gender,
    this.id,
    this.image,
    this.location.name,
    this.name,
    this.species,
    this.status,
)