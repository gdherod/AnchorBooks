package com.anchorbooks.bestseller.model.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import timber.log.Timber

@Database(
    entities = [BookEntity::class, BookDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}

class BookApplication : Application() {
    companion object {
        var bookDatabase: BookDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate de application")
        bookDatabase = Room.databaseBuilder(this, BookDatabase::class.java, "book_database").build()
    }


}