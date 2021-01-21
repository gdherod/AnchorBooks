package com.anchorbooks.bestseller.model.repository

import androidx.lifecycle.MutableLiveData
import com.anchorbooks.bestseller.model.db.BookApplication
import com.anchorbooks.bestseller.model.db.BookDetailEntity
import com.anchorbooks.bestseller.model.db.mapperBookApiToDB
import com.anchorbooks.bestseller.model.db.mapperBookDetailApiToDB
import com.anchorbooks.bestseller.model.remote.RetrofitClient
import timber.log.Timber

class BookRepository {
    private val bookDatabase = BookApplication.bookDatabase!!
    val books = bookDatabase.bookDao().getBooks()
    val bookDetail: MutableLiveData<BookDetailEntity> = MutableLiveData()
    suspend fun listBooks() {
        Timber.d("listBooks")
        val response = RetrofitClient.retrofitInstance().booksList()

        when {
            response.isSuccessful -> response.body()?.let { list ->
                val res = list.map {
                    mapperBookApiToDB(it)
                }
                bookDatabase.bookDao().insert(res)
            }
            else -> Timber.d("${response.errorBody()}")
        }
    }

    suspend fun bookDetail(id: Int) {
        Timber.d("bookDetail")

        val response = RetrofitClient.retrofitInstance().bookDetail(id)

        when {
            response.isSuccessful -> response.body()?.let {
                bookDetail.value = mapperBookDetailApiToDB(it)
            }
            else -> Timber.d("${response.errorBody()}")
        }
    }
}