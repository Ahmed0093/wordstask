package com.example.domain.wordlist.repository

import com.example.domain.wordlist.model.WordDomainModel

interface WordsRepository {

    suspend fun getAllWords(): List<WordDomainModel>
}