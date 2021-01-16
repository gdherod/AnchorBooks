package com.anchorbooks.bestseller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.anchorbooks.bestseller.ui.listing.BookListingFragment
import com.anchorbooks.bestseller.ui.vm.BookViewModel
import com.anchorbooks.bestseller.utils.TimberInitializer

class MainActivity : AppCompatActivity() {
    private val timberInitializer = TimberInitializer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timberInitializer.initTimber()
        supportFragmentManager.beginTransaction().add(R.id.activity_main_container, BookListingFragment()).commit()
    }
}