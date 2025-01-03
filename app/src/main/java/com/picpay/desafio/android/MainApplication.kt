package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.AppModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            //modules(AppModule().module)
        }

    }
}