package com.anchorbooks.bestseller.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    suspend fun insert(books: List<BookEntity>)

    @Query("SELECT * FROM book_table")
    fun getBooks(): LiveData<List<BookEntity>>

    @Insert
    suspend fun insert(book: BookDetailEntity)

    @Query("SELECT * FROM book_detail_table WHERE id= :id")
    fun getBook(id: Int): LiveData<BookDetailEntity>
}