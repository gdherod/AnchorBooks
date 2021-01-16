package com.anchorbooks.bestseller.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.anchorbooks.bestseller.databinding.FragmentBookDetailBinding
import com.anchorbooks.bestseller.ui.vm.BookViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import timber.log.Timber

class BookDetailFragment : Fragment() {
    private lateinit var binding: FragmentBookDetailBinding

    private val bookVM: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailBinding.inflate(layoutInflater)

        bookVM.bookSelected().observe(viewLifecycleOwner, {
            Timber.d("El libro seleccionado es $it")
        })
        bookVM.bookDetail().observe(viewLifecycleOwner, {

            Glide.with(binding.bookDetailImg.context)
                .load(it.imageLink)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.bookDetailImg)
            binding.bookDetailTitle.text = it.title
            binding.bookDetailAuthor.text = it.author
            binding.bookDetailCountry.text = it.country
            binding.bookDetailLanguage.text = it.language
            binding.bookDetailLink.text = it.link
            binding.bookDetailPages.text = it.pages.toString()
            binding.bookDetailYear.text = it.year.toString()
            binding.bookDetailPrice.text = it.price.toString()
            binding.bookDetailLastprice.text = it.lastPrice.toString()
            binding.bookDetailDelivery.text = it.delivery.toString()
        })
        return binding.root
    }
}