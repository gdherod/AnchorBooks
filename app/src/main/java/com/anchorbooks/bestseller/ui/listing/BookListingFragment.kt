package com.anchorbooks.bestseller.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.anchorbooks.bestseller.R
import com.anchorbooks.bestseller.databinding.FragmentBookListingBinding
import com.anchorbooks.bestseller.ui.adapter.BookAdapter
import com.anchorbooks.bestseller.ui.detail.BookDetailFragment
import com.anchorbooks.bestseller.ui.vm.BookViewModel
import timber.log.Timber

class BookListingFragment : Fragment() {
    private lateinit var binding: FragmentBookListingBinding

    private val bookVM: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListingBinding.inflate(layoutInflater)
        binding.booksList.layoutManager = LinearLayoutManager(context)
        val adapter = BookAdapter()
        binding.booksList.adapter = adapter

        adapter.bookSelected().observe(viewLifecycleOwner, {
            Timber.d("$it seleccionado")
            bookVM.bookSelected(it)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.activity_main_container, BookDetailFragment())
                ?.addToBackStack("Detalle")?.commit()
        })

        bookVM.books().observe(viewLifecycleOwner, {
            adapter.update(it)
        })
        return binding.root
    }
}