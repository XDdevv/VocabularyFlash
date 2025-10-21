package zed.rainxch.vocabularyflash.features.practice_deck.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import zed.rainxch.vocabularyflash.core.presentation.design_system.theme.AppTheme

@Composable
fun PracticeDeckRoot(
    viewModel: PracticeDeckViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PracticeDeckScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun PracticeDeckScreen(
    state: PracticeDeckState,
    onAction: (PracticeDeckAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        PracticeDeckScreen(
            state = PracticeDeckState(),
            onAction = {}
        )
    }
}