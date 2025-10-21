package zed.rainxch.vocabularyflash.features.home.presentation.mappers

import zed.rainxch.vocabularyflash.core.domain.model.Deck
import zed.rainxch.vocabularyflash.core.presentation.utils.toDeckColor
import zed.rainxch.vocabularyflash.features.home.presentation.models.DeckUi

fun Deck.toDeckUi(): DeckUi {
    return DeckUi(
        id = id,
        title = title,
        description = description,
        totalWords = words.size,
        color = colorId.toDeckColor()
    )
}