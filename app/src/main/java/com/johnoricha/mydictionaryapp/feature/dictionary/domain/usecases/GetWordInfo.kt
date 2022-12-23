package com.johnoricha.mydictionaryapp.feature.dictionary.domain.usecases

import com.johnoricha.mydictionaryapp.core.util.Resource
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.WordInfoRepository
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.models.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {  }
        }

        return repository.getWordInfo(word)

    }
}