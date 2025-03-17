package com.example.data.repository

import com.example.database.localdatabase.WordEntity

interface WordsLocalDataSource {
    fun getWordsFromDB(): List<WordEntity>
    fun insertWord(word: WordEntity)
    fun insertWords(word: List<WordEntity>)
    fun clear()
}