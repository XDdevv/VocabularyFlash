package zed.rainxch.vocabularyflash.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.vocabularyflash.core.domain.model.Deck
import zed.rainxch.vocabularyflash.core.domain.utils.DeckColorsConstants
import zed.rainxch.vocabularyflash.features.home.presentation.mappers.toDeckUi

class HomeViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadSampleData()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HomeState()
        )

    private fun loadSampleData() {
        viewModelScope.launch {
            val decks = listOf(
                Deck(
                    1,
                    "Title",
                    "Augue non mauris ante viverra ut arcu sed ut lectus interdum morbi sed leo purus gravida non id mi augue.",
                    words = listOf(),
                    colorId = DeckColorsConstants.CRYSTAL_CLEAR_ID
                ),
                Deck(
                    2,
                    "Title2",
                    "Augue non mauris ante viverra ut arcu sed ut lectus interdum morbi sed leo purus gravida non id mi augue.",
                    words = listOf(),
                    colorId = DeckColorsConstants.ORANGE_CORAL_ID
                )
            ).map { it.toDeckUi() }

            _state.update {
                it.copy(
                    decks = decks.toImmutableList()
                )
            }
        }
    }

}