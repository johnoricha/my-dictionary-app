package com.johnoricha.mydictionaryapp.feature.dictionary.data

import androidx.room.*
import com.johnoricha.mydictionaryapp.feature.dictionary.data.local.Converters
import com.johnoricha.mydictionaryapp.feature.dictionary.data.local.WordInfoDao
import com.johnoricha.mydictionaryapp.feature.dictionary.data.local.entity.WordInfoEntity


@Database(
    entities = [WordInfoEntity::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract val dao: WordInfoDao

}