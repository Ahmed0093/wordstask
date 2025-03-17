package com.example.data

import com.example.database.localdatabase.WordEntity
import com.example.domain.wordlist.model.WordDomainModel

fun convertToDomainModel(entityWords: List<WordEntity>): List<WordDomainModel> {
    val wordDomainList = ArrayList<WordDomainModel>()
    entityWords.forEach {
        wordDomainList.add(it.toWordDomainModel())
    }
    return wordDomainList
}

private fun WordEntity.toWordDomainModel(): WordDomainModel {
    return WordDomainModel(this.name, this.repeatedCount)
}
