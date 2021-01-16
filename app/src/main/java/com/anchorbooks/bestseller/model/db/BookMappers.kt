package com.anchorbooks.bestseller.model.db

import com.anchorbooks.bestseller.model.remote.pojo.Book

fun mapperBookApiToDB(book: Book): BookEntity {
    return BookEntity(book.id, book.author, book.country, book.imageLink, book.language, book.title)
}

fun mapperBookDBToApi(bookEntity: BookEntity): Book {
    return Book(bookEntity.id, bookEntity.author, bookEntity.country, bookEntity.imageLink, bookEntity.language, bookEntity.title)
}