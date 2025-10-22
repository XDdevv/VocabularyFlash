package zed.rainxch.vocabularyflash

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import vocabularyflash.composeapp.generated.resources.Res
import vocabularyflash.composeapp.generated.resources.app_name
import vocabularyflash.composeapp.generated.resources.compose_multiplatform
import zed.rainxch.vocabularyflash.app.di.initKoin

fun main() {
    initKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = stringResource(Res.string.app_name),
            icon = painterResource(Res.drawable.compose_multiplatform)
        ) {
            App()
        }
    }
}