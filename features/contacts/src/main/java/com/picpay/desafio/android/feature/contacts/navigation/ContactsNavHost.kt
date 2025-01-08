package com.picpay.desafio.android.feature.contacts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun ContactsNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ContactsDestination.ROUTE
    ) {
        registerContactsGraph()
    }
}