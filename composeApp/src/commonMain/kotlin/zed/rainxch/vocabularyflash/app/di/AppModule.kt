package zed.rainxch.vocabularyflash.app.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import zed.rainxch.vocabularyflash.features.home.presentation.HomeViewModel
import zed.rainxch.vocabularyflash.features.new_deck.presentation.NewDeckViewModel
import zed.rainxch.vocabularyflash.features.practice_deck.presentation.PracticeDeckViewModel

val appModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::PracticeDeckViewModel)
    viewModelOf(::NewDeckViewModel)
}