package zed.rainxch.vocabularyflash.features.new_deck.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zed.rainxch.vocabularyflash.core.domain.model.Word
import zed.rainxch.vocabularyflash.core.domain.utils.DeckColorsConstants
import zed.rainxch.vocabularyflash.features.new_deck.domain.repository.NewDeckRepository

class NewDeckViewModel (
    private val repository: NewDeckRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(NewDeckState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = NewDeckState()
        )

    fun onAction(action: NewDeckAction) {
        when (action) {
            NewDeckAction.OnActionButtonClick -> {
                createNewDeck()
            }
        }
    }

    private fun createNewDeck() {
        viewModelScope.launch {
            repository.createNewDeck(
                title = "Hello world",
                description = "This is first deck or desk",
                colorId = DeckColorsConstants.CRYSTAL_CLEAR_ID,
                words = listOf(
                    Word(
                        word = "hi",
                        meaning = "salom",
                        example = "Hello world"
                    ),
                    Word(
                        word = "hi",
                        meaning = "salom",
                        example = "Hello world"
                    ),
                    Word(
                        word = "hi",
                        meaning = "salom",
                        example = "Hello world"
                    ),
                )
            )
        }
    }

}