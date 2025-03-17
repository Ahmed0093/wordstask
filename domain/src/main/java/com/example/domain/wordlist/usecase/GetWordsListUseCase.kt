package com.example.domain.wordlist.usecase

import com.example.domain.wordlist.model.WordDomainModel
import com.example.domain.wordlist.repository.WordsRepository
import javax.inject.Inject

class GetWordsListUseCase @Inject constructor(
    private val repo: WordsRepository
) {
    suspend operator fun invoke(): List<WordDomainModel> {
        return repo.getAllWords()
    }
}
