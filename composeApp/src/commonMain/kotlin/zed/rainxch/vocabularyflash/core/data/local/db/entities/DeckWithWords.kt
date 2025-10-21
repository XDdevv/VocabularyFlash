package zed.rainxch.vocabularyflash.core.data.local.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class DeckWithWords(
    @Embedded val deck: DeckEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "deckId"
    )
    val words: List<WordEntity>,
)