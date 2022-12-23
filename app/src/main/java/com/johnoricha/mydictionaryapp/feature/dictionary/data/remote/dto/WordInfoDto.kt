package com.johnoricha.mydictionaryapp.feature.dictionary.data.remote.dto

import com.johnoricha.mydictionaryapp.feature.dictionary.data.local.entity.WordInfoEntity
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.models.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity = WordInfoEntity(
        meanings = meanings.map{item -> item.toMeaning()},
        phonetic = phonetic,
        word = word
    )
}