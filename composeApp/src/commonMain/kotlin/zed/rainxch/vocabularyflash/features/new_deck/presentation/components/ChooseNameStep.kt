package zed.rainxch.vocabularyflash.features.new_deck.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import vocabularyflash.composeapp.generated.resources.Res
import vocabularyflash.composeapp.generated.resources.deck_description_helper
import vocabularyflash.composeapp.generated.resources.deck_description_placeholder
import vocabularyflash.composeapp.generated.resources.deck_name_helper
import vocabularyflash.composeapp.generated.resources.deck_name_placeholder
import vocabularyflash.composeapp.generated.resources.pick_your_favorite_color
import zed.rainxch.vocabularyflash.core.presentation.design_system.text_fields.VocabularyFlashTextField
import zed.rainxch.vocabularyflash.core.presentation.utils.DeckColorsMapper
import zed.rainxch.vocabularyflash.features.new_deck.presentation.NewDeckAction
import zed.rainxch.vocabularyflash.features.new_deck.presentation.NewDeckState

@Composable
fun ChooseNameStep(
    state: NewDeckState,
    onAction: (NewDeckAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        VocabularyFlashTextField(
            value = state.name,
            onValueChange = { name ->
                onAction(NewDeckAction.OnNameChange(name))
            },
            placeholder = stringResource(Res.string.deck_name_placeholder),
            error = state.errors["name"]?.toText(),
            helper = stringResource(Res.string.deck_name_helper),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        VocabularyFlashTextField(
            value = state.description,
            onValueChange = { description ->
                onAction(NewDeckAction.OnDescriptionChange(description))
            },
            placeholder = stringResource(Res.string.deck_description_placeholder),
            error = state.errors["description"]?.toText(),
            helper = stringResource(Res.string.deck_description_helper),
            modifier = Modifier.fillMaxWidth(),
            lines = 3
        )
        Spacer(Modifier.height(48.dp))

        Text(
            text = stringResource(Res.string.pick_your_favorite_color),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DeckColorsMapper.deckColors.forEach { (id, color) ->
                Box(
                    Modifier
                        .size(50.dp)
                        .clip(CutCornerShape(50f))
                        .background(brush = color)
                        .combinedClickable(
                            onClick = {
                                onAction(NewDeckAction.OnColorChange(id))
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.colorId == id) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}