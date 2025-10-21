package zed.rainxch.vocabularyflash.features.home.domain.repository

import kotlinx.coroutines.flow.Flow
import zed.rainxch.vocabularyflash.core.domain.model.Deck

interface HomeRepository {
    fun getDecks() : Flow<List<Deck>>
}