package com.example.wordstask.di

import com.example.data.repository.WordsRepositoryImpl
import com.example.domain.wordlist.repository.WordsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun provideWordsRepo(wordsRepositoryImpl: WordsRepositoryImpl): WordsRepository
}