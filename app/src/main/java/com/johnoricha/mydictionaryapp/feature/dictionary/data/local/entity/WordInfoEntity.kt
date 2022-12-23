package com.johnoricha.mydictionaryapp.feature.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.models.Meaning
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.models.WordInfo


@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String? = null,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            phonetic = phonetic,
            word = word
        )
    }
}
