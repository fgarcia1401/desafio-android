package com.picpay.desafio.android.contacts.util


import android.app.Application
import com.picpay.android.common.di.CommomModule
import com.picpay.desafio.android.commontest.TestNetworkConfigurationProvider
import com.picpay.desafio.android.core.network.providers.NetworkConfigurationProvider
import com.picpay.desafio.android.core.network.retrofit.NetworkModule
import com.picpay.desafio.android.feature.contacts.di.ContactsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.koin.dsl.module

class TestContactsApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(this@TestContactsApplication)
                modules(
                    ContactsModule().module,
                    CommomModule().module,
                    NetworkModule().module,
                    module {
                        single<NetworkConfigurationProvider> { TestNetworkConfigurationProvider() }
                    }
                )
            }
        }
    }

}