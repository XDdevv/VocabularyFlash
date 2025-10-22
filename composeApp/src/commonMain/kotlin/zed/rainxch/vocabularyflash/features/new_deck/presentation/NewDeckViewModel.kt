package zed.rainxch.vocabularyflash.features.new_deck.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vocabularyflash.composeapp.generated.resources.Res
import vocabularyflash.composeapp.generated.resources.error_description_required
import vocabularyflash.composeapp.generated.resources.error_minimum_words
import vocabularyflash.composeapp.generated.resources.error_name_required
import zed.rainxch.vocabularyflash.core.domain.model.Word
import zed.rainxch.vocabularyflash.core.presentation.utils.TextUi
import zed.rainxch.vocabularyflash.features.new_deck.domain.repository.NewDeckRepository
import zed.rainxch.vocabularyflash.features.new_deck.presentation.model.NewDeckStep

class NewDeckViewModel(
    private val repository: NewDeckRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(NewDeckState())
    val state = _state.asStateFlow()

    fun onAction(action: NewDeckAction) {
        when (action) {
            is NewDeckAction.OnAddedNewWord -> {
                val words = _state.value.words.toMutableList()
                _state.value.currentWord.let { word ->
                    words.add(word)
                }
                _state.update {
                    it.copy(
                        currentWord = Word(
                            id = it.currentWord.id + 1,
                            word = "",
                            meaning = "",
                            example = ""
                        ),
                        words = words.toImmutableList()
                    )
                }
            }

            is NewDeckAction.OnColorChange -> {
                _state.update { it.copy(colorId = action.colorId) }
            }

            is NewDeckAction.OnDeletedWord -> {
                val words = _state.value.words.toMutableList()
                words.remove(action.word)
                _state.update { it.copy(words = words.toImmutableList()) }
            }

            is NewDeckAction.OnNameChange -> {
                _state.update { it.copy(name = action.name) }
            }

            is NewDeckAction.OnDescriptionChange -> {
                _state.update { it.copy(description = action.description) }
            }

            NewDeckAction.OnNextStepClick -> {
                _state.update { it.copy(isNavigatingForward = true) }

                when (_state.value.currentStep) {
                    NewDeckStep.ChooseName -> {
                        if (validateNameInfo() && validateDescriptionInfo()) {
                            _state.update { it.copy(currentStep = NewDeckStep.CreateWords) }
                        }
                    }

                    NewDeckStep.CreateWords -> {
                        if (validateWords()) {
                            _state.update { it.copy(currentStep = NewDeckStep.Complete) }
                        }
                    }

                    NewDeckStep.Complete -> {
                        viewModelScope.launch(Dispatchers.IO) {
                            repository.createNewDeck(
                                title = _state.value.name,
                                description = _state.value.description,
                                colorId = _state.value.colorId,
                                words = _state.value.words
                            )
                        }
                    }
                }
            }

            NewDeckAction.OnPreviousStepClick -> {
                _state.update { it.copy(isNavigatingForward = false) }

                _state.update {
                    it.copy(
                        currentStep = when (it.currentStep) {
                            NewDeckStep.CreateWords -> NewDeckStep.ChooseName
                            NewDeckStep.Complete -> NewDeckStep.CreateWords
                            else -> it.currentStep
                        }
                    )
                }
            }

            is NewDeckAction.OnCurrentWordExampleChange -> {
                _state.update {
                    it.copy(
                        currentWord = it.currentWord.copy(example = action.example)
                    )
                }
            }

            is NewDeckAction.OnCurrentWordMeaningChange -> {
                _state.update {
                    it.copy(
                        currentWord = it.currentWord.copy(meaning = action.meaning)
                    )
                }
            }

            is NewDeckAction.OnCurrentWordWordChange -> {
                _state.update {
                    it.copy(
                        currentWord = it.currentWord.copy(word = action.word)
                    )
                }
            }

            else -> {}
        }
    }

    private fun validateNameInfo(): Boolean {
        val errors = _state.value.errors.toMutableMap()
        if (_state.value.name.isBlank()) {
            errors["name"] = TextUi.Dynamic(Res.string.error_name_required)
        } else {
            errors.remove("name")
        }
        _state.update { it.copy(errors = errors) }
        return !errors.containsKey("name")
    }

    private fun validateDescriptionInfo(): Boolean {
        val errors = _state.value.errors.toMutableMap()
        if (_state.value.description.isBlank()) {
            errors["description"] = TextUi.Dynamic(Res.string.error_description_required)
        } else {
            errors.remove("description")
        }
        _state.update { it.copy(errors = errors) }
        return !errors.containsKey("description")
    }

    private fun validateWords(): Boolean {
        val errors = _state.value.errors.toMutableMap()
        if (_state.value.words.size < 3) {
            errors["words"] = TextUi.Dynamic(Res.string.error_minimum_words)
        } else {
            errors.remove("words")
        }
        _state.update { it.copy(errors = errors) }
        return !errors.containsKey("words")
    }


}