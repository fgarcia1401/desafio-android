package com.picpay.desafio.android.core.network.providers

import com.picpay.desafio.android.core.network.BuildConfig
import org.koin.core.annotation.Single

@Single
class StandardNetworkConfigurationProvider : NetworkConfigurationProvider {
    override fun getBaseUrl() = BuildConfig.BASE_URL
}
