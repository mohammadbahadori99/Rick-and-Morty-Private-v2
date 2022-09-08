package com.example.rickandmorty.model

import com.example.domain.model.CharacterDomainModel

fun CharacterDomainModel.toCharacterView() = CharacterView(
    gender, id, image, locationName, name, species, status
)