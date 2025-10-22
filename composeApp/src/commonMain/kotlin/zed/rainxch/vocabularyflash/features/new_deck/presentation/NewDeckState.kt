package zed.rainxch.vocabularyflash.features.new_deck.presentation

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import zed.rainxch.vocabularyflash.core.domain.model.Word
import zed.rainxch.vocabularyflash.core.domain.utils.DeckColorsConstants
import zed.rainxch.vocabularyflash.core.presentation.utils.TextUi
import zed.rainxch.vocabularyflash.features.new_deck.presentation.model.NewDeckStep

data class NewDeckState(
    val currentStep: NewDeckStep = NewDeckStep.ChooseName,
    val name: String = "",
    val description: String = "",
    val colorId: String = DeckColorsConstants.RAINBOW_BLUE_ID,
    val words: ImmutableList<Word> = persistentListOf(),
    val isNavigatingForward: Boolean = true,
    val errors: Map<String, TextUi> = emptyMap(),
    val currentWord: Word = Word(word = "", meaning = "", example = ""),
)