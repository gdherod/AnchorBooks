package com.anchorbooks.bestseller.model.remote

import com.anchorbooks.bestseller.model.remote.pojo.Book
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BookAPI {
    @GET("books/")
    suspend fun booksList(): Response<List<Book>>
}

class RetrofitClient {
    companion object {

        private const val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/anchorBooks/"

        fun retrofitInstance(): BookAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(BookAPI::class.java)
        }
    }
}