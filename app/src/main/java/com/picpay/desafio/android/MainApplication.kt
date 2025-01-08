package com.picpay.desafio.android

import android.app.Application
import com.picpay.android.common.di.CommomModule
import com.picpay.desafio.android.core.network.retrofit.NetworkModule
import com.picpay.desafio.android.di.AppModule
import com.picpay.desafio.android.feature.contacts.di.ContactsModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.ksp.generated.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(
                AppModule().module,
                NetworkModule().module,
                ContactsModule().module,
                CommomModule().module
            )
        }

    }
}