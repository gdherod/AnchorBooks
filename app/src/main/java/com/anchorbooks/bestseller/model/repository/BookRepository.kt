package com.anchorbooks.bestseller.model.repository

import androidx.lifecycle.MutableLiveData
import com.anchorbooks.bestseller.model.remote.RetrofitClient
import com.anchorbooks.bestseller.model.remote.pojo.Book
import com.anchorbooks.bestseller.model.remote.pojo.BookDetail
import timber.log.Timber

class BookRepository {
    val books : MutableLiveData<List<Book>> = MutableLiveData()
    val bookDetail: MutableLiveData<BookDetail> = MutableLiveData()
    suspend fun listBooks() {
        Timber.d("listBooks")
        val response = RetrofitClient.retrofitInstance().booksList()

        when {
            response.isSuccessful -> response.body()?.let {
                books.value = it
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