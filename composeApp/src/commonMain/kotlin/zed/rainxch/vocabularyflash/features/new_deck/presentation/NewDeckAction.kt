package zed.rainxch.vocabularyflash.features.new_deck.presentation

import zed.rainxch.vocabularyflash.core.domain.model.Word

sealed interface NewDeckAction {

    data object OnBackClick : NewDeckAction
    data object OnDismissAddWordBottomSheet : NewDeckAction
    data object OnAddWordFABClick : NewDeckAction
    data class OnNameChange(val name: String) : NewDeckAction
    data class OnDescriptionChange(val description: String) : NewDeckAction
    data class OnColorChange(val colorId: String) : NewDeckAction
    data object OnAddedNewWord : NewDeckAction
    data class OnDeletedWord(val word: Word) : NewDeckAction
    data object OnPreviousStepClick : NewDeckAction
    data object OnNextStepClick : NewDeckAction
    data class OnCurrentWordWordChange(val word: String) : NewDeckAction
    data class OnCurrentWordMeaningChange(val meaning: String) : NewDeckAction
    data class OnCurrentWordExampleChange(val example: String) : NewDeckAction
}