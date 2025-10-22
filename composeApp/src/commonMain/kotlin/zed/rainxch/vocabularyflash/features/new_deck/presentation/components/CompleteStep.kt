package zed.rainxch.vocabularyflash.features.new_deck.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import vocabularyflash.composeapp.generated.resources.Res
import vocabularyflash.composeapp.generated.resources.verify_details
import zed.rainxch.vocabularyflash.features.new_deck.presentation.NewDeckState

@Composable
fun CompleteStep(
    state: NewDeckState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(Res.string.verify_details),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground
        )

        DeckPreview(
            title = state.name,
            description = state.description,
            words = state.words,
        )
    }
}