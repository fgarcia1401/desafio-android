package com.picpay.desafio.android.feature.contacts.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.registerContactsGraph() {
    composable(ContactsDestination.ROUTE) {
        ContactsDestination.screen.invoke()
    }
}
