package com.example.wordstask.di

import android.content.Context
import androidx.room.Room
import com.example.database.localdatabase.AppDatabase
import com.example.database.localdatabase.WordsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "word_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): WordsDao {
        return database.wordsDao()
    }
}