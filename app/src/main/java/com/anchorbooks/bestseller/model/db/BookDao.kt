package com.anchorbooks.bestseller.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anchorbooks.bestseller.model.remote.pojo.BookDetail

@Dao
interface BookDao {

    @Insert
    suspend fun insert(books: List<BookEntity>)

    @Query("SELECT * FROM book_table")
    fun getBooks(): LiveData<List<BookEntity>>

    @Insert
    suspend fun insert(book: BookDetail)

    @Query("SELECT * FROM book_detail_table WHERE id= :id")
    fun getBook(id: Int): LiveData<BookDetail>
}