package zed.rainxch.vocabularyflash.app.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import zed.rainxch.vocabularyflash.core.data.local.db.AppDatabase
import zed.rainxch.vocabularyflash.features.home.data.local.db.dao.HomeDao
import zed.rainxch.vocabularyflash.features.home.data.repository.HomeRepositoryImpl
import zed.rainxch.vocabularyflash.features.home.domain.repository.HomeRepository
import zed.rainxch.vocabularyflash.features.home.presentation.HomeViewModel
import zed.rainxch.vocabularyflash.features.new_deck.presentation.NewDeckViewModel
import zed.rainxch.vocabularyflash.features.practice_deck.presentation.PracticeDeckViewModel

val appModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::PracticeDeckViewModel)
    viewModelOf(::NewDeckViewModel)

    singleOf(::HomeRepositoryImpl) bind HomeRepository::class

    single<HomeDao> {
        get<AppDatabase>().homeDao
    }
}