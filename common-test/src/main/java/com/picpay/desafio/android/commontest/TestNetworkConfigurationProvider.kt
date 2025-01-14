package com.picpay.desafio.android.commontest

import com.picpay.desafio.android.core.network.BuildConfig
import com.picpay.desafio.android.core.network.providers.NetworkConfigurationProvider

class TestNetworkConfigurationProvider : NetworkConfigurationProvider {

    override fun getBaseUrl() = BuildConfig.BASE_URL_MOCK

}
