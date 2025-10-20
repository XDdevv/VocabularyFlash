package zed.rainxch.vocabularyflash

import androidx.compose.ui.window.ComposeUIViewController
import zed.rainxch.vocabularyflash.app.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}