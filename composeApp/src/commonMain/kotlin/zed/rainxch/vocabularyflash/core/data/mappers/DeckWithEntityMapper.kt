package zed.rainxch.vocabularyflash.core.data.mappers

import zed.rainxch.vocabularyflash.core.data.local.db.entities.DeckWithWords
import zed.rainxch.vocabularyflash.core.domain.model.Deck

fun DeckWithWords.toDeck(): Deck {
    return Deck(
        id = this.deck.id,
        title = this.deck.title,
        description = this.deck.description,
        words = this.words.map { it.toWord() },
        colorId = this.deck.colorId
    )
}