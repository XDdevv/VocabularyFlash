package zed.rainxch.vocabularyflash.core.domain.model

import zed.rainxch.vocabularyflash.core.domain.utils.DeckColorsConstants

data class Deck(
    val id: Int,
    val title: String,
    val description: String?,
    val words: List<Word>,
    val colorId: String = DeckColorsConstants.RAINBOW_BLUE_ID,
)
