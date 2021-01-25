package com.anchorbooks.bestseller

import com.anchorbooks.bestseller.model.db.mapperBookApiToDB
import com.anchorbooks.bestseller.model.remote.pojo.Book
import org.junit.Test

class MappersTest {

    @Test
    fun mapperBookApiToDB_happyCase() {
        // Given
        val book = Book(
            0,
            "quwerty",
            "Chile",
            "asdf",
            "la",
            "title"
        )

        // When
        val response = mapperBookApiToDB(book)

        // Then
        assert(response.id == book.id)
        assert(response.author == book.author)
        assert(response.country == book.country)
        assert(response.imageLink == book.imageLink)
        assert(response.language == book.language)
        assert(response.title == book.title)
    }
}