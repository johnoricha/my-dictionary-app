package com.johnoricha.mydictionaryapp.feature.dictionary.data.remote

import android.telephony.mbms.StreamingServiceInfo
import com.johnoricha.mydictionaryapp.feature.dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL: String = "https://api.dictionaryapi.dev/"

interface DictionaryApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWordInfos(
        @Path("word") word: String
    ): List<WordInfoDto>
}