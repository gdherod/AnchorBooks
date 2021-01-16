package com.anchorbooks.bestseller.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anchorbooks.bestseller.model.remote.pojo.Book
import com.anchorbooks.bestseller.model.remote.pojo.BookDetail
import com.anchorbooks.bestseller.model.repository.BookRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class BookViewModel : ViewModel() {
    private val repository = BookRepository()

    private val books = repository.books

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