package zed.rainxch.vocabularyflash.features.new_deck.presentation

sealed interface NewDeckEvents {
    data object OnDeckCreatedSuccessfully : NewDeckEvents
    data class OnDeckCreateFailure (val message: String) : NewDeckEvents
}