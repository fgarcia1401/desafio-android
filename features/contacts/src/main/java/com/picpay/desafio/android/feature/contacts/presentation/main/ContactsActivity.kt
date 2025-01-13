package com.picpay.desafio.android.feature.contacts.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.feature.contacts.navigation.ContactsNavHost
import com.picpay.desafio.android.core.compose.theme.MyApplicationTheme

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                ContactsNavHost()
            }
        }
    }
}