package com.anchorbooks.bestseller.model.repository

import androidx.lifecycle.MutableLiveData
import com.anchorbooks.bestseller.model.db.BookApplication
import com.anchorbooks.bestseller.model.db.mapperBookApiToDB
import com.anchorbooks.bestseller.model.remote.RetrofitClient
import com.anchorbooks.bestseller.model.remote.pojo.Book
import com.anchorbooks.bestseller.model.remote.pojo.BookDetail
import timber.log.Timber

class BookRepository {
    private val booksDatabase = BookApplication.bookDatabase!!
    val books = booksDatabase.bookDao().getBooks()
    val bookDetail: MutableLiveData<BookDetail> = MutableLiveData()
    suspend fun listBooks() {
        Timber.d("listBooks")
        val response = RetrofitClient.retrofitInstance().booksList()

        when {
            response.isSuccessful -> response.body()?.let {
                    list ->
                val res = list.map {
                    mapperBookApiToDB(it)
                }
                booksDatabase.bookDao().insert(res)
            }
            else -> Timber.d("${response.errorBody()}")
        }
    }

    suspend fun bookDetail(id : Int) {
        Timber.d("bookDetail")

        val response = RetrofitClient.retrofitInstance().bookDetail(id)

        when {
            response.isSuccessful -> response.body()?.let {
                bookDetail.value = it
            }
            else -> Timber.d("${response.errorBody()}")
        }
    }
}