package com.johnoricha.mydictionaryapp.feature.dictionary.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import com.google.gson.Gson
import com.johnoricha.mydictionaryapp.feature.dictionary.data.WordInfoDatabase
import com.johnoricha.mydictionaryapp.feature.dictionary.data.WordInfoRepoImpl
import com.johnoricha.mydictionaryapp.feature.dictionary.data.local.Converters
import com.johnoricha.mydictionaryapp.feature.dictionary.data.local.WordInfoDao
import com.johnoricha.mydictionaryapp.feature.dictionary.data.remote.BASE_URL
import com.johnoricha.mydictionaryapp.feature.dictionary.data.remote.DictionaryApi
import com.johnoricha.mydictionaryapp.feature.dictionary.data.util.GsonParser
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.WordInfoRepository
import com.johnoricha.mydictionaryapp.feature.dictionary.domain.usecases.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun providesWordInfoRepository(
        api: DictionaryApi,
        db: WordInfoDatabase
    ): WordInfoRepository {
        return WordInfoRepoImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun providesWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_info_db"
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun providesDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(
                DictionaryApi::
                class.java
            )
    }
}