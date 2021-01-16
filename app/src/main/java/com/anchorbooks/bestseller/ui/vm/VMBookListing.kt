package com.anchorbooks.bestseller.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anchorbooks.bestseller.model.repository.Repository
import kotlinx.coroutines.launch
import timber.log.Timber

class VMBookListing : ViewModel() {
    private val repository = Repository()

    init {
        Timber.d("Cargando el listado de libros")
        viewModelScope.launch {
            repository.listBooks()
        }
    }
}