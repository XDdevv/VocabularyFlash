package zed.rainxch.vocabularyflash.features.new_deck.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import zed.rainxch.vocabularyflash.core.presentation.design_system.theme.AppTheme

@Composable
fun NewDeckRoot(
    viewModel: NewDeckViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NewDeckScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun NewDeckScreen(
    state: NewDeckState,
    onAction: (NewDeckAction) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                onAction(NewDeckAction.OnActionButtonClick)
            }
        ) {
            Text(text = "Add new deck")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        NewDeckScreen(
            state = NewDeckState(),
            onAction = {}
        )
    }
}