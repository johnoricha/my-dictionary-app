package com.johnoricha.mydictionaryapp.feature.dictionary.domain.models

import com.johnoricha.mydictionaryapp.feature.dictionary.data.remote.dto.DefinitionDto

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String,
)
