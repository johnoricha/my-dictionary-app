package com.johnoricha.mydictionaryapp.feature.dictionary.data

import com.johnoricha.mydictionaryapp.core.util.Resource
import com.johnoricha.mydictionaryapp.feature.dictionary.data.local.WordInfoDao
import com.johnoricha.mydictionaryapp.feature.dictionary.data.remote.DictionaryApi
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.WordInfoRepository
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.models.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepoImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfo(word).map { it.toWordInfo() }




        try {
            val remoteWordInfos = api.getWordInfos(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfo(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "oops! something went wrong",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Could not reach server",
                    data = wordInfos
                )
            )
        }
        val newWordInfos = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(
            data = newWordInfos
        ))
    }



}