package com.johnoricha.mydictionaryapp.feature.dictionary.data.remote.dto

import com.johnoricha.mydictionaryapp.feature.dictionary.domain.models.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions.map { item -> item.toDefinition() },
            partOfSpeech = partOfSpeech,
        )
    }
}