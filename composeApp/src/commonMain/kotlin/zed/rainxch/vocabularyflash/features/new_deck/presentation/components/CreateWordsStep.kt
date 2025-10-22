package zed.rainxch.vocabularyflash.features.new_deck.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import vocabularyflash.composeapp.generated.resources.Res
import vocabularyflash.composeapp.generated.resources.add_new_word_title
import vocabularyflash.composeapp.generated.resources.add_word_button
import vocabularyflash.composeapp.generated.resources.add_words_helper_example
import vocabularyflash.composeapp.generated.resources.add_words_helper_meaning
import vocabularyflash.composeapp.generated.resources.add_words_helper_word
import vocabularyflash.composeapp.generated.resources.add_words_placeholder_example
import vocabularyflash.composeapp.generated.resources.add_words_placeholder_meaning
import vocabularyflash.composeapp.generated.resources.add_words_placeholder_word
import vocabularyflash.composeapp.generated.resources.empty_words_message
import vocabularyflash.composeapp.generated.resources.words_title
import zed.rainxch.vocabularyflash.core.presentation.design_system.containers.EmptyState
import zed.rainxch.vocabularyflash.core.presentation.design_system.text_fields.VocabularyFlashTextField
import zed.rainxch.vocabularyflash.features.new_deck.presentation.NewDeckAction
import zed.rainxch.vocabularyflash.features.new_deck.presentation.NewDeckState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWordsStep(
    state: NewDeckState,
    onAction: (NewDeckAction) -> Unit,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    onAction(NewDeckAction.OnAddWordFABClick)
                },
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = {
                    Text(stringResource(Res.string.add_word_button))
                }
            )
        }
    ) { padding ->
        val padding = PaddingValues(
            start = 0.dp,
            end = 0.dp,
            top = 0.dp,
            bottom = padding.calculateBottomPadding(),
        )

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 8.dp)
        ) {
            item {
                Text(
                    text = stringResource(Res.string.words_title),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                if (state.errors["words"] != null) {
                    Text(
                        text = state.errors["words"]?.toText().toString(),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = MaterialTheme.colorScheme.error,
                    )
                }

                if (state.words.isEmpty()) {
                    EmptyState(
                        message = stringResource(Res.string.empty_words_message),
                        icon = Icons.AutoMirrored.Filled.ListAlt,
                        textStyle = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp)
                    )
                }
            }

            items(
                items = state.words,
                key = { word -> word.id }
            ) { word ->
                NewDeckWordItem(
                    word = word,
                    onDeleteWord = { onAction(NewDeckAction.OnDeletedWord(word)) },
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .animateItem()
                )
            }
        }
    }

    if (state.isNewWordBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                onAction(NewDeckAction.OnDismissAddWordBottomSheet)
            },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(Res.string.add_new_word_title),
                    style = MaterialTheme.typography.headlineSmall
                )

                VocabularyFlashTextField(
                    value = state.currentWord.word,
                    onValueChange = { onAction(NewDeckAction.OnCurrentWordWordChange(it)) },
                    helper = stringResource(Res.string.add_words_helper_word),
                    placeholder = stringResource(Res.string.add_words_placeholder_word),
                    modifier = Modifier.fillMaxWidth(),
                    error = state.errors["new_word"]?.toText()
                )

                VocabularyFlashTextField(
                    value = state.currentWord.meaning,
                    onValueChange = { onAction(NewDeckAction.OnCurrentWordMeaningChange(it)) },
                    helper = stringResource(Res.string.add_words_helper_meaning),
                    placeholder = stringResource(Res.string.add_words_placeholder_meaning),
                    modifier = Modifier.fillMaxWidth(),
                    error = state.errors["new_meaning"]?.toText()
                )

                VocabularyFlashTextField(
                    value = state.currentWord.example,
                    onValueChange = { onAction(NewDeckAction.OnCurrentWordExampleChange(it)) },
                    lines = 4,
                    helper = stringResource(Res.string.add_words_helper_example),
                    placeholder = stringResource(Res.string.add_words_placeholder_example),
                    modifier = Modifier.fillMaxWidth(),
                    error = state.errors["new_example"]?.toText()
                )

                Button(
                    onClick = {
                        onAction(NewDeckAction.OnAddedNewWord)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(Res.string.add_word_button))
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
