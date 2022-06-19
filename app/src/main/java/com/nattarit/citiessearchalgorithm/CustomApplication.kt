package com.nattarit.citiessearchalgorithm

import android.app.Application
import android.util.Log
import com.nattarit.citiessearchalgorithm.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CustomApplication : Application() {
    private val TAG = javaClass.simpleName
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: Application Start!")
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CustomApplication)
            modules(appModule)
        }
    }

}