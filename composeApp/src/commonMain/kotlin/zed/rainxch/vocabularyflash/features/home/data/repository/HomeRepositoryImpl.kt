package zed.rainxch.vocabularyflash.features.home.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import zed.rainxch.vocabularyflash.core.data.mappers.toDeck
import zed.rainxch.vocabularyflash.core.domain.model.Deck
import zed.rainxch.vocabularyflash.features.home.data.local.db.dao.HomeDao
import zed.rainxch.vocabularyflash.features.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val homeDao: HomeDao,
) : HomeRepository {
    override fun getDecks(): Flow<List<Deck>> {
        return homeDao
            .getAllDecksWithWords()
            .map { deckWithWords -> deckWithWords.map { words -> words.toDeck() } }
            .flowOn(Dispatchers.Default)
    }
}