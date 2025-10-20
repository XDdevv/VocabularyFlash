package zed.rainxch.vocabularyflash

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import zed.rainxch.vocabularyflash.app.di.initKoin

fun main() {
    initKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Vocabulary Flash",
        ) {
            App()
        }
    }
}