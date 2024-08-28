package com.example.majkomulti

import android.app.Application
import com.example.majkomulti.di.KoinInjector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MajkoApp: Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInjector.koinApp
            .androidLogger(Level.NONE)
            .androidContext(this@MajkoApp)
    }

}

