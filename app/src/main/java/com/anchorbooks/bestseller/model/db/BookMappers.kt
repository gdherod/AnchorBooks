package com.anchorbooks.bestseller.model.db

import com.anchorbooks.bestseller.model.remote.pojo.Book
import com.anchorbooks.bestseller.model.remote.pojo.BookDetail

fun mapperBookApiToDB(book: Book): BookEntity {
    return BookEntity(
        book.id,
        book.author,
        book.country,
        book.imageLink,
        book.language,
        book.title
    )
}

fun mapperBookDBToApi(bookEntity: BookEntity): Book {
    return Book(
        bookEntity.id,
        bookEntity.author,
        bookEntity.country,
        bookEntity.imageLink,
        bookEntity.language,
        bookEntity.title
    )
}

fun mapperBookDetailApiToDB(bookDetail: BookDetail): BookDetailEntity {
    return BookDetailEntity(
        bookDetail.id,
        bookDetail.author,
        bookDetail.country,
        bookDetail.imageLink,
        bookDetail.language,
        bookDetail.link,
        bookDetail.pages,
        bookDetail.title,
        bookDetail.year,
        bookDetail.price,
        bookDetail.lastPrice,
        bookDetail.delivery
    )
}