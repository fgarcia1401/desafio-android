package com.picpay.desafio.android.contacts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import com.picpay.desafio.android.feature.contacts.presentation.main.ContactsActivity

class ContactsScreenRobot(private val composeTestRule: ComposeContentTestRule) {

    private var scenario: ActivityScenario<ContactsActivity>? = null

    fun launchActivityScenario() {
        scenario = ActivityScenario.launch(ContactsActivity::class.java)
    }

    fun closeActivityScenario() {
        scenario?.close()
    }

    fun verify(block: ContactsScreenVerification.() -> Unit) {
        ContactsScreenVerification(composeTestRule).block()
    }

}

class ContactsScreenVerification(private val composeTestRule: ComposeContentTestRule) {
    fun isListDisplayedWithTitle() {
        composeTestRule.onNodeWithText("Lista de Contatos").assertIsDisplayed()
        composeTestRule.onNodeWithText("Desafio Android").assertIsDisplayed()
    }

    fun isLoadingIndicatorDisplayed() {
        composeTestRule.onNodeWithText("Carregando...").assertIsDisplayed()
    }

    fun isErrorSnackbarDisplayed(errorMessage: String) {
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    fun areContactDetailsDisplayed(contactName: String) {
        composeTestRule.onNodeWithText(contactName).assertIsDisplayed()
    }
}
