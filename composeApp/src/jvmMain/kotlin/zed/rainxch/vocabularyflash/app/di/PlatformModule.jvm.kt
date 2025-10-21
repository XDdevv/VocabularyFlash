package zed.rainxch.vocabularyflash.app.di

import org.koin.dsl.module
import zed.rainxch.vocabularyflash.app.database.getAppDatabase
import zed.rainxch.vocabularyflash.core.data.local.db.AppDatabase

actual val platformModule = module {
    single<AppDatabase> {
        getAppDatabase()
    }
}