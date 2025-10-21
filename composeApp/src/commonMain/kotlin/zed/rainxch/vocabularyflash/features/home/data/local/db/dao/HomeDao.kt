package zed.rainxch.vocabularyflash.features.home.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import zed.rainxch.vocabularyflash.core.data.local.db.entities.DeckWithWords

@Dao
interface HomeDao {

    @Transaction
    @Query("SELECT * FROM decks_table")
    fun getAllDecksWithWords(): Flow<List<DeckWithWords>>



}