package com.example.data.repository

import com.example.database.localdatabase.WordEntity
import com.example.database.localdatabase.WordsDao
import javax.inject.Inject

class WordsLocalDataSourceImpl @Inject constructor(private val wordsDao: WordsDao) :
    WordsLocalDataSource {
    override fun getWordsFromDB(): List<WordEntity> {
        return wordsDao.getAllWords()
    }

    override fun insertWord(word: WordEntity) {
        wordsDao.insertWord(word)
    }

    override fun insertWords(words: List<WordEntity>) {
        wordsDao.insertWords(words)
    }

    override fun clear() {
        wordsDao.clearAll()
    }


}