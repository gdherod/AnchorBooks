package com.anchorbooks.bestseller.model.remote.pojo

data class Book(
    val id: Int,
    val author: String,
    val country: String,
    val imageLink: String,
    val language: String,
    val title: String
)