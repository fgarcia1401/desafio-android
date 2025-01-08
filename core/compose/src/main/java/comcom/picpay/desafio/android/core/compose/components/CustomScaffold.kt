package comcom.picpay.desafio.android.core.compose.components

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.PaddingValues

@Composable
fun CustomScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable () -> Unit,
    snackBarHostState: SnackbarHostState = scaffoldState.snackbarHostState,
    backgroundColorSnackBar: Color = Color.Red,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = topBar,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { data ->
                Snackbar(
                    backgroundColor = backgroundColorSnackBar,
                    snackbarData = data,
                    contentColor = Color.White
                )
            }
        },
        content = content
    )
}