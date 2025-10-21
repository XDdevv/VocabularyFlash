package zed.rainxch.vocabularyflash.features.new_deck.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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