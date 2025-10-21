package zed.rainxch.vocabularyflash.features.new_deck.domain.repository

import zed.rainxch.vocabularyflash.core.domain.model.Word

interface NewDeckRepository {
    suspend fun createNewDeck(
        title: String,
        description: String?,
        colorId: String,
        words: List<Word>
    )
}