package zed.rainxch.vocabularyflash.features.home.presentation

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import zed.rainxch.vocabularyflash.features.home.presentation.models.DeckUi

data class HomeState(
    val decks: ImmutableList<DeckUi> = persistentListOf(),
)