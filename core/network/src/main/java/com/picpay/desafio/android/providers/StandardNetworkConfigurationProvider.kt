package com.picpay.desafio.android.providers

import com.picpay.desafio.android.BuildConfig
import org.koin.core.annotation.Factory

@Factory
class StandardNetworkConfigurationProvider : NetworkConfigurationProvider {
    override fun getBaseUrl() = BuildConfig.BASE_URL
}
