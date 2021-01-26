package com.anchorbooks.bestseller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anchorbooks.bestseller.databinding.ActivityMainBinding
import com.anchorbooks.bestseller.ui.listing.BookListingFragment
import com.anchorbooks.bestseller.utils.TimberInitializer

class MainActivity : AppCompatActivity() {
    private val timberInitializer = TimberInitializer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timberInitializer.initTimber()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_container, BookListingFragment()).commit()
    }
}