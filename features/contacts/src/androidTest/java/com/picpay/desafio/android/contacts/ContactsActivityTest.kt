package com.picpay.desafio.android.contacts

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.commontest.relaxedMock
import com.picpay.desafio.android.commontest.MockWebServerRule
import com.picpay.desafio.android.feature.contacts.data.datasource.local.ContactLocalSource
import io.mockk.coEvery
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class ContactsActivityTest : KoinTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun verifyIsTitleToolbarAnSubTitleDisplay() {
        ContactsScreenRobot(composeTestRule).apply {
            launchActivityScenario()
            verify {
                isListDisplayedWithTitle()
            }
            closeActivityScenario()
        }
    }

    @Test
    fun verifyListContactDisplay() {
        mockWebServerRule.enqueueResponseFromFile("contacts_response.json")

        ContactsScreenRobot(composeTestRule).apply {
            launchActivityScenario()
            verify {
                areContactDetailsDisplayed("User mockado")
                areContactDetailsDisplayed("@mock")
                areContactDetailsDisplayed("Usuário Mock")
                areContactDetailsDisplayed("@mockuser")
            }
            closeActivityScenario()
        }
    }

    @Test
    fun verifyShowSnackErrorWithErrorContactsResponseAndLocalIsEmpty() {
        mockLocalSource()

        mockWebServerRule.enqueueErrorResponse(404)

        ContactsScreenRobot(composeTestRule).apply {
            launchActivityScenario()
            verify {
                isErrorSnackbarDisplayed("Ocorreu um erro ao carregar lista. Tente novamente mais tarde.")
            }
            closeActivityScenario()
        }
    }

    private fun mockLocalSource() {
        val localSource: ContactLocalSource = relaxedMock()
        coEvery {
            localSource.getContactsLocal()
        } returns listOf()

        loadKoinModules(
            module { factory { localSource } }
        )
    }

}
