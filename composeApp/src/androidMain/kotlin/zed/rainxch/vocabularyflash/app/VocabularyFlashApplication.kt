package zed.rainxch.vocabularyflash.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import zed.rainxch.vocabularyflash.app.di.initKoin

class VocabularyFlashApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@VocabularyFlashApplication)
        }
    }
}