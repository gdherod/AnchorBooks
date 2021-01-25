package com.anchorbooks.bestseller.ui.detail

import android.content.Intent
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
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class BookDetailFragment : Fragment() {
    private lateinit var binding: FragmentBookDetailBinding
    private val deliveryYes = "Cuenta con despacho"
    private val deliveryNo = "Sin despacho"

    private val bookVM: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailBinding.inflate(layoutInflater)

        bookVM.bookDetail().observe(viewLifecycleOwner, {
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
            when {
                (it.delivery) ->
                    binding.bookDetailDelivery.text = deliveryYes
                else -> binding.bookDetailDelivery.text = deliveryNo
            }

            fun email() {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("ventas@anchorBooks.cl"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta por libro ${it.title}, id ${it.id}")
                intent.putExtra(
                    Intent.EXTRA_TEXT, "Hola" +
                            "Vi el libro ${it.title} de código ${it.id} y me gustaría que me contactaran a este correo o al" +
                            "siguiente número +56 9 ________" +
                            "Quedo atento."
                )
                intent.type = "message/rfc822"
                startActivity(Intent.createChooser(intent, "Email del cliente"))
            }

            binding.floatingMailButton.setOnClickListener { view ->
                Snackbar.make(view, "Email", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
                email()
            }
        })



        return binding.root
    }
}