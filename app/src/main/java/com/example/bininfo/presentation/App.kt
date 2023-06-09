package com.example.bininfo.presentation

import android.app.Application
import com.example.bininfo.presentation.di.moduleBin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@App)

            modules(moduleBin)
        }
    }
}