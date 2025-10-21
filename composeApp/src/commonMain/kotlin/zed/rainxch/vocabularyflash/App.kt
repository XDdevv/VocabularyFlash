package zed.rainxch.vocabularyflash

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.vocabularyflash.core.presentation.design_system.theme.AppTheme
import zed.rainxch.vocabularyflash.navigation.AppNavigation

@Composable
@Preview
fun App() {
    AppTheme {
        AppNavigation()
    }
}