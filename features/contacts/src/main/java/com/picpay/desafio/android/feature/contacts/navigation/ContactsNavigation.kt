package com.picpay.desafio.android.feature.contacts.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object ContactsNavigation {
    const val START_DESTINATION = ContactsDestination.ROUTE
}

fun NavGraphBuilder.registerContactsGraph() {
    composable(ContactsDestination.ROUTE) {
        ContactsDestination.screen.invoke()
    }
}