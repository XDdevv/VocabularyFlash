package zed.rainxch.vocabularyflash.core.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("decks_table")
data class DeckEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,
    val colorId: String,
)
