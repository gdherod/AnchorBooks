package com.anchorbooks.bestseller.model.repository

import androidx.lifecycle.MutableLiveData
import com.anchorbooks.bestseller.model.remote.RetrofitClient
import com.anchorbooks.bestseller.model.remote.pojo.Book
import timber.log.Timber

class Repository {
    val books : MutableLiveData<List<Book>> = MutableLiveData()
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
}