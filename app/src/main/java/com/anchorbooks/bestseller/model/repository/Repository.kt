package com.anchorbooks.bestseller.model.repository

import com.anchorbooks.bestseller.model.remote.RetrofitClient
import timber.log.Timber

class Repository {
    suspend fun listBooks() {
        Timber.d("listBooks")
        val response = RetrofitClient.retrofitInstance().booksList()

        when {
            response.isSuccessful -> response.body()?.let {
                Timber.d("tenemos ${it.size} libros")
            }
            else -> Timber.d("${response.errorBody()}")
        }
    }
}