package com.anchorbooks.bestseller.ui.vm

import androidx.lifecycle.*
import com.anchorbooks.bestseller.model.db.BookDetailEntity
import com.anchorbooks.bestseller.model.db.mapperBookDBToApi
import com.anchorbooks.bestseller.model.remote.pojo.Book
import com.anchorbooks.bestseller.model.repository.BookRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class BookViewModel : ViewModel() {
    private val repository = BookRepository()

    fun books(): LiveData<List<Book>> = books

    private val books = Transformations.map(repository.books) { entities ->
        entities.map {
            mapperBookDBToApi(it)
        }
    }

    init {
        Timber.d("Cargando el listado de libros")
        viewModelScope.launch {
            repository.listBooks()
        }
    }

    //fun bookSelected(): LiveData<Book> = bookSelected

    private val bookSelected = MutableLiveData<Book>()

    fun bookDetail(): LiveData<BookDetailEntity> = repository.bookDetail

    fun bookSelected(book: Book) {
        bookSelected.value = book
        viewModelScope.launch {
            repository.bookDetail(book.id)
        }
    }

}