package com.example.database.localdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordsDao {
    @Query("SELECT * FROM words")
    fun getAllWords(): List<WordEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: WordEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWords(word: List<WordEntity>)

    @Query("DELETE FROM words")
    fun clearAll()
}