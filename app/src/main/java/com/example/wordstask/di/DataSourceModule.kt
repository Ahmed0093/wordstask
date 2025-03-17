package com.example.wordstask.di

import com.example.data.remote.ParseHtmlDataSource
import com.example.data.remote.ParseHtmlDataSourceImpl
import com.example.data.repository.WordsLocalDataSource
import com.example.data.repository.WordsLocalDataSourceImpl
import com.example.database.localdatabase.WordsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideParseHtmlDataSource(
        httpClient: OkHttpClient,
        @Named("baseUrl")  baseUrl: String
    ): ParseHtmlDataSource {
        return ParseHtmlDataSourceImpl(httpClient, baseUrl)
    }

    @Singleton
    @Provides
    fun provideWordsLocalDataSource(wordsDao: WordsDao): WordsLocalDataSource {
        return WordsLocalDataSourceImpl(wordsDao)
    }
}