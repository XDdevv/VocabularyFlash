package zed.rainxch.vocabularyflash.features.new_deck.presentation

sealed interface NewDeckAction {
    data object OnActionButtonClick : NewDeckAction
}