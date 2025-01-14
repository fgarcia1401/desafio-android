package com.picpay.desafio.android.feature.contacts.navigation

import androidx.compose.runtime.Composable
import com.picpay.desafio.android.feature.contacts.presentation.list.screen.ContactsScreen

object ContactsDestination {
    const val ROUTE = "contacts_destination"
    val screen = @Composable { ContactsScreen() }
}
