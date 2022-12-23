package com.johnoricha.mydictionaryapp.feature.dictionary.domain.models

data class Definition(
    val antonyms: List<String>? = null,
    val definition: String,
    val example: String?,
    val synonyms: List<String>? = null
)
