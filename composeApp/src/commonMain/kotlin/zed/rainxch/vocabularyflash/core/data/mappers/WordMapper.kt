package zed.rainxch.vocabularyflash.core.data.mappers

import zed.rainxch.vocabularyflash.core.data.local.db.entities.WordEntity
import zed.rainxch.vocabularyflash.core.domain.model.Word

fun WordEntity.toWord(): Word {
    return Word(
        id = this.id,
        word = this.word,
        meaning = this.meaning,
        example = this.example
    )
}