package com.picpay.desafio.android.feature.contacts.presentation.list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.picpay.desafio.android.core.compose.theme.ColorPrimaryDark
import com.picpay.desafio.android.core.compose.theme.Dimens.PaddingMedium
import com.picpay.desafio.android.feature.contacts.R
import com.picpay.desafio.android.feature.contacts.domain.model.User

@Composable
fun ContactItem(
    contact: User,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(ColorPrimaryDark),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(52.dp),
            contentAlignment = Alignment.Center
        ) {
            ShowAvatar(contact)
        }

        Column(
            modifier = Modifier
                .padding(start = PaddingMedium)
        ) {
            Text(
                text = contact.name,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = contact.username,
                color = Color.LightGray,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
private fun ShowAvatar(contact: User) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(contact.img)
                .crossfade(true)
                .error(R.drawable.ic_account_place_holder)
                .build(),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            loading = { Loading() }
        )
    }
}

@Composable
private fun Loading() {
    CircularProgressIndicator(
        color = Color.White,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
fun UserListItemPreview() {
    ContactItem(
        User(
            img = "https://example.com/image.jpg",
            username = "@username",
            name = "Name Surname",
            id = 1
        ),
        modifier = Modifier
    )
}
