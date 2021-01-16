package com.anchorbooks.bestseller.utils

import com.anchorbooks.bestseller.BuildConfig
import timber.log.Timber

class TimberInitializer {

    fun initTimber() {
        when {
            BuildConfig.DEBUG -> Timber.plant(Timber.DebugTree())
        }
    }
}