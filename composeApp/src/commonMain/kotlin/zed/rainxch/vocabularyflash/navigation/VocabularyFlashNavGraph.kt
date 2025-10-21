package zed.rainxch.vocabularyflash.navigation

import kotlinx.serialization.Serializable

sealed interface VocabularyFlashNavGraph {
    @Serializable
    data object HomeScreen : VocabularyFlashNavGraph

    @Serializable
    data class PracticeDeckScreen(
        val deckId: Int,
    ) : VocabularyFlashNavGraph

    @Serializable
    data object NewDeckScreen : VocabularyFlashNavGraph

}