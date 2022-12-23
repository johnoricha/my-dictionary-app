package com.johnoricha.mydictionaryapp.feature.dictionary.domain.models

class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val word: String
)