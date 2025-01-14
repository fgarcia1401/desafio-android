package com.picpay.desafio.android.feature.contacts.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.picpay.desafio.android.feature.contacts.navigation.ContactsNavHost
import com.picpay.desafio.android.core.compose.theme.MyApplicationTheme

class ContactsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                ContactsNavHost()
            }
        }
    }
}
