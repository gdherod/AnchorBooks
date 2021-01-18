package com.anchorbooks.bestseller.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(books: List<BookEntity>)

    @Query("SELECT * FROM book_table")
    fun getBooks(): LiveData<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookDetail: BookDetailEntity)

    @Query("SELECT * FROM book_detail_table WHERE id= :id")
    fun getBookDetail(id: Int): LiveData<BookDetailEntity>
}