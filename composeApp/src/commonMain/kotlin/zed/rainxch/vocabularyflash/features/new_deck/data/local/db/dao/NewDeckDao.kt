package zed.rainxch.vocabularyflash.features.new_deck.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Upsert
import zed.rainxch.vocabularyflash.core.data.local.db.entities.DeckEntity
import zed.rainxch.vocabularyflash.core.data.local.db.entities.WordEntity

@Dao
interface NewDeckDao {
    @Upsert
    suspend fun insertDeck(deckEntity: DeckEntity): Long

    @Insert
    suspend fun insertWords(words: List<WordEntity>)
}