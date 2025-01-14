package com.picpay.desafio.android.contacts.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class KoinTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestContactsApplication::class.java.name, context)
    }
}