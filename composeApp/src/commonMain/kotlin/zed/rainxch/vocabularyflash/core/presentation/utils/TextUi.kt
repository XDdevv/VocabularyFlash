package zed.rainxch.vocabularyflash.core.presentation.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed class TextUi {
    data class Dynamic(
        val res: StringResource,
    ) : TextUi()

    data class Static(
        val text: String,
    ) : TextUi()

    @Composable
    fun toText(): String {
        return when (this) {
            is Dynamic -> stringResource(res)
            is Static -> text
        }
    }

}