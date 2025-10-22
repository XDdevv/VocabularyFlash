package zed.rainxch.vocabularyflash.features.new_deck.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import vocabularyflash.composeapp.generated.resources.Res
import vocabularyflash.composeapp.generated.resources.confirm_create_deck
import vocabularyflash.composeapp.generated.resources.next
import vocabularyflash.composeapp.generated.resources.previous
import zed.rainxch.vocabularyflash.features.new_deck.presentation.model.NewDeckStep

@Composable
fun NewDeckNavigationButtons(
    currentStep: NewDeckStep,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPreviousClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(Res.string.previous),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Button(
            onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.weight(1f)
        ) {
            val nextText = if (currentStep == NewDeckStep.Complete) {
                stringResource(Res.string.confirm_create_deck)
            } else {
                stringResource(Res.string.next)
            }

            Text(
                text = nextText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
        }
    }

}