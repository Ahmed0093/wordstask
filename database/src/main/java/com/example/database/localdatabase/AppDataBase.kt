package com.example.database.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WordEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao
}

