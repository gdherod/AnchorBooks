package com.anchorbooks.bestseller.ui.vm

import androidx.lifecycle.*
import com.anchorbooks.bestseller.model.db.mapperBookDBToApi
import com.anchorbooks.bestseller.model.remote.pojo.Book
import com.anchorbooks.bestseller.model.remote.pojo.BookDetail
import com.anchorbooks.bestseller.model.repository.BookRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class BookViewModel : ViewModel() {
    private val repository = BookRepository()

    private val books = Transformations.map(repository.books) {
        entities -> entities.map {
            mapperBookDBToApi(it)
    }
    }

    private val bookSelected = MutableLiveData<Book>()

    fun bookDetail(): LiveData<BookDetail> = repository.bookDetail

    init {
        Timber.d("Cargando el listado de libros")
        viewModelScope.launch {
            repository.listBooks()
        }
    }

    fun books(): LiveData<List<Book>> = books

    fun bookSelected(): LiveData<Book> = bookSelected

    fun bookSelected(book: Book) {
        bookSelected.value = book
        viewModelScope.launch {
            repository.bookDetail(book.id)
        }
    }

}