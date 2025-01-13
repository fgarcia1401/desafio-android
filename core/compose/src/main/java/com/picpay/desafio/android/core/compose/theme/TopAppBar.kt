package com.picpay.desafio.android.core.compose.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center
            )
       },
        modifier = modifier,
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
}

@Preview("MyTopAppBar Preview")
@Composable
fun MyTopAppBarPreview(){
    TopAppBar(title = "Desafio Android")
}