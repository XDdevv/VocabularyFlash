package zed.rainxch.vocabularyflash.features.new_deck.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import zed.rainxch.vocabularyflash.core.data.local.db.entities.DeckEntity
import zed.rainxch.vocabularyflash.core.data.local.db.entities.WordEntity
import zed.rainxch.vocabularyflash.core.domain.model.Word
import zed.rainxch.vocabularyflash.features.new_deck.data.local.db.dao.NewDeckDao
import zed.rainxch.vocabularyflash.features.new_deck.domain.repository.NewDeckRepository

class NewDeckRepositoryImpl(
    private val newDeckDao: NewDeckDao,
) : NewDeckRepository {
    override suspend fun createNewDeck(
        title: String,
        description: String?,
        colorId: String,
        words: List<Word>,
    ) = withContext(Dispatchers.IO) {
        val deck = DeckEntity(
            title = title,
            description = description,
            colorId = colorId
        )

        val deckId = newDeckDao.insertDeck(deck).toInt()

        val wordEntities = words.map { word ->
            WordEntity(
                deckId = deckId,
                word = word.word,
                meaning = word.meaning,
                example = word.example
            )
        }

        newDeckDao.insertWords(wordEntities)
    }

}