package zed.rainxch.vocabularyflash.features.new_deck.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import vocabularyflash.composeapp.generated.resources.Res
import vocabularyflash.composeapp.generated.resources.practice_title
import vocabularyflash.composeapp.generated.resources.practice_description
import vocabularyflash.composeapp.generated.resources.show_all_words
import vocabularyflash.composeapp.generated.resources.show_less_words
import vocabularyflash.composeapp.generated.resources.words_title
import zed.rainxch.vocabularyflash.core.domain.model.Word

@Composable
fun DeckPreview(
    title: String,
    description: String,
    words: ImmutableList<Word>,
    modifier: Modifier = Modifier,
) {
    var isListExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(Res.string.practice_title, title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = stringResource(Res.string.practice_description, description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = stringResource(Res.string.words_title),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = if (isListExpanded) {
                    words
                } else words.take(3),
                key = { it.id }
            ) { word ->
                NewDeckWordItem(
                    word = word,
                    showDelete = false,
                    modifier = Modifier.animateItem()
                )
            }

            item {
                if (words.size > 3) {
                    OutlinedButton(
                        onClick = { isListExpanded = !isListExpanded },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val text = if (isListExpanded) {
                            stringResource(Res.string.show_less_words)
                        } else {
                            stringResource(Res.string.show_all_words)
                        }

                        Text(
                            text = text,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}