package zed.rainxch.vocabularyflash.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import zed.rainxch.vocabularyflash.core.data.local.db.entities.DeckEntity
import zed.rainxch.vocabularyflash.core.data.local.db.entities.WordEntity
import zed.rainxch.vocabularyflash.features.home.data.local.db.dao.HomeDao
import zed.rainxch.vocabularyflash.features.new_deck.data.local.db.dao.NewDeckDao

@Database(entities = [DeckEntity::class, WordEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val homeDao: HomeDao
    abstract val newDeckDao: NewDeckDao
}