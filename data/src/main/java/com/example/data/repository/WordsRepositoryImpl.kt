package com.example.data.repository

import com.example.data.convertToDomainModel
import com.example.data.remote.ParseHtmlDataSource
import com.example.database.localdatabase.WordEntity
import com.example.domain.wordlist.NoInternetException
import com.example.domain.wordlist.model.WordDomainModel
import com.example.domain.wordlist.repository.WordsRepository
import org.jsoup.Jsoup
import javax.inject.Inject

class WordsRepositoryImpl @Inject constructor(
    private val wordsLocalDataSource: WordsLocalDataSource,
    private val parseHtmlDataSource: ParseHtmlDataSource
) : WordsRepository {
    override suspend fun getAllWords(): List<WordDomainModel> {
        val localWords = wordsLocalDataSource.getWordsFromDB()

        try {
            val response = parseHtmlDataSource.getParsedHtmlResponse()
            val document = Jsoup.parse(response.body?.string())
            val text = document.text()
            val words = text.split("\\s+".toRegex()).map { str ->
                str.replace(
                    Regex("[^\\p{IsArabic}A-Za-z0-9 ]"),
                    ""
                ).lowercase() // Replace special characters with an empty string
            }.filter { it.isNotBlank() }

            val listOfWords: ArrayList<WordEntity> = ArrayList<WordEntity>()
            val wordCountMap = words.groupingBy { it }.eachCount()
            words.toSet().forEach {
                listOfWords.add(WordEntity(name = it, repeatedCount = wordCountMap[it] ?: 0))
            }
            wordsLocalDataSource.clear()
            wordsLocalDataSource.insertWords(listOfWords)
            return convertToDomainModel(listOfWords)
        } catch (e: java.io.IOException) {
            if (localWords.isNotEmpty()) return convertToDomainModel(localWords)
            throw NoInternetException()
        } catch (e: Exception) {
            throw e
        }
    }
}
