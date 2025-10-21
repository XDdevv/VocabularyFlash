package zed.rainxch.vocabularyflash.features.home.presentation

import zed.rainxch.vocabularyflash.features.home.presentation.models.DeckUi

sealed interface HomeAction {
    data object OnCreateNewDeckClick : HomeAction

    data class OnDeckClick(val deck: DeckUi) : HomeAction
}