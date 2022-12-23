package com.johnoricha.mydictionaryapp.feature.dictionary.domain

import com.johnoricha.mydictionaryapp.core.util.Resource
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.models.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}