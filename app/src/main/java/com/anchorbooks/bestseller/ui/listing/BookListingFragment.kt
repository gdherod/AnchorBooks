package com.anchorbooks.bestseller.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.anchorbooks.bestseller.databinding.FragmentBookListingBinding
import com.anchorbooks.bestseller.ui.adapter.BookAdapter
import com.anchorbooks.bestseller.ui.vm.VMBookListing

class BookListingFragment : Fragment() {
    private lateinit var binding: FragmentBookListingBinding

    private val bookVM: VMBookListing by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListingBinding.inflate(layoutInflater)
        binding.booksList.layoutManager = LinearLayoutManager(context)
        val adapter = BookAdapter()
        binding.booksList.adapter = adapter

        bookVM.books().observe(viewLifecycleOwner, {
            adapter.update(it)
        })
        return binding.root
    }
}