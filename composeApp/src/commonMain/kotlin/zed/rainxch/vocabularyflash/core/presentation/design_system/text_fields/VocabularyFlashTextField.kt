package zed.rainxch.vocabularyflash.core.presentation.design_system.text_fields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun VocabularyFlashTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    error: String? = null,
    helper: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    lines: Int = 1,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            placeholder?.let { placeholder ->
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        supportingText = {
            if (error == null && helper != null) {
                Text(
                    text = helper,
                    style = MaterialTheme.typography.titleSmall
                )
            } else if (error != null) {
                Text(
                    text = error,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        minLines = lines,
        maxLines = lines,
        modifier = modifier
    )
}