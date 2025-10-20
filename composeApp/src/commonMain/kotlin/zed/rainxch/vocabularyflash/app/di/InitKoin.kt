package zed.rainxch.vocabularyflash.app.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    val modules = listOf(appModule, platformModule)

    startKoin {
        config?.invoke(this)
        modules(modules)
    }
}