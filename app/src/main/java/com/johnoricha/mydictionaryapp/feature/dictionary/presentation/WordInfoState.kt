package com.johnoricha.mydictionaryapp.feature.dictionary.presentation

import com.johnoricha.mydictionaryapp.feature.dictionary.domain.models.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
