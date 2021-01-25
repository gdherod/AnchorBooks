package com.anchorbooks.bestseller

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.anchorbooks.bestseller.model.db.BookDao
import com.anchorbooks.bestseller.model.db.BookDatabase
import com.anchorbooks.bestseller.model.db.BookEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.*

class BooksDataBaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var bookDao: BookDao
    private lateinit var database: BookDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java).build()
        bookDao = database.bookDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertBooks_empty() = runBlocking {
        // Given
        val bookList = listOf<BookEntity>()

        // When
        bookDao.insert(bookList)

        // Then
        bookDao.getBooks().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertBooks_1book() = runBlocking {
        // Given
        val bookList = listOf<BookEntity>(
            BookEntity
                (
                0,
                "qwerty",
                "Chile",
                "asdf",
                "la",
                "title"
            )
        )

        // When
        bookDao.insert(bookList)

        // Then
        bookDao.getBooks().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertThat("com.anchorbooks.bestseller").isEqualTo(appContext.packageName)
    }
}