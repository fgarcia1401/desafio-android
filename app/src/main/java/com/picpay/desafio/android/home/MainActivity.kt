package com.picpay.desafio.android.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.feature.contacts.presentation.main.ContactsActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToContacts()
    }


    private fun navigateToContacts() {
        startActivity(Intent(this, ContactsActivity::class.java))
        finish()
    }

}
