package com.anchorbooks.bestseller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anchorbooks.bestseller.utils.TimberInitializer

class MainActivity : AppCompatActivity() {
    private val timberInitializer = TimberInitializer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timberInitializer.initTimber()
    }
}