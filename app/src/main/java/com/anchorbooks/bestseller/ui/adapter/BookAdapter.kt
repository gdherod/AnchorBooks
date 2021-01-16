package com.anchorbooks.bestseller.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.anchorbooks.bestseller.databinding.BookItemListingBinding
import com.anchorbooks.bestseller.model.remote.pojo.Book
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var booksList = listOf<Book>()

    private val bookSelected = MutableLiveData<Book>()

    fun bookSelected(): LiveData<Book> = bookSelected

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemListingBinding.inflate(LayoutInflater.from(parent.context))

        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = booksList[position]
        holder.bind(book)
        holder.itemView.setOnClickListener {
            bookSelected.value = book
        }
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    fun update(pBooksList: List<Book>) {
        booksList = pBooksList
        notifyDataSetChanged()
    }

    class BookViewHolder(private val binding: BookItemListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.bookTitleItem.text = book.title
            Glide.with(binding.bookImgList.context)
                .load(book.imageLink)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.bookImgList)
            binding.bookAuthorItem.text = book.author
            binding.bookCountryItem.text = book.country
            binding.bookLanguageItem.text = book.language
        }
    }
}