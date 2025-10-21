package zed.rainxch.vocabularyflash.features.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.vocabularyflash.core.presentation.design_system.theme.AppTheme

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        HomeScreen(
            state = HomeState(),
            onAction = {}
        )
    }
}