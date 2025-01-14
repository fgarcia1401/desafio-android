package com.picpay.desafio.android.feature.contacts.presentation.list.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.picpay.android.common.coroutines.collectWithLifecycle
import com.picpay.desafio.android.core.compose.components.CustomScaffold
import com.picpay.desafio.android.core.compose.theme.ColorAccent
import com.picpay.desafio.android.core.compose.theme.ColorPrimaryDark
import com.picpay.desafio.android.core.compose.theme.Dimens.PaddingDefault
import com.picpay.desafio.android.core.compose.theme.Dimens.PaddingLarge
import com.picpay.desafio.android.core.compose.theme.Dimens.PaddingMedium
import com.picpay.desafio.android.core.compose.theme.TopAppBar
import com.picpay.desafio.android.feature.contacts.R
import com.picpay.desafio.android.feature.contacts.presentation.list.ContactsViewModel
import com.picpay.desafio.android.feature.contacts.presentation.list.ListContactsEvent
import com.picpay.desafio.android.feature.contacts.presentation.list.ListContactsState
import com.picpay.desafio.android.feature.contacts.presentation.list.ListContactsUIEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = koinViewModel(),
    context: Context = LocalContext.current
) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ListContactsEvent.Initialize)
    }

    ObserveEvents(viewModel, context, scaffoldState.snackbarHostState)

    CustomScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = stringResource(R.string.toolbar_title))
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ColorPrimaryDark)
        ) {
            Text(
                text = stringResource(R.string.title_list),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = PaddingLarge),
                textAlign = TextAlign.Center
            )

            Divider(modifier = Modifier.padding(PaddingMedium), color = LightGray.copy(alpha = 0.5f))

            ShowList(state)

            if (state.showLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = ColorAccent
                )
            }
        }
    }
}

@Composable
private fun ShowList(state: ListContactsState) {
    val list = state.list
    val size = list.size

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = PaddingMedium),
        contentPadding = PaddingValues(horizontal = PaddingMedium)
    ) {
        items(size) { index ->
            val contact = list[index]
            ContactItem(
                contact,
                modifier = Modifier.padding(vertical = PaddingDefault)
            )
        }
    }
}

@Composable
private fun ObserveEvents(
    viewModel: ContactsViewModel,
    context: Context,
    snackBarHostState: SnackbarHostState
) {
    viewModel.uiEvent.collectWithLifecycle { event ->
        when (event) {
            is ListContactsUIEvent.ShowError -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(event.messageResId),
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun ContactsScreenPreview() {
    ContactsScreen()
}
