package zed.rainxch.vocabularyflash

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Vocabulary Flash",
    ) {
        App()
    }
}