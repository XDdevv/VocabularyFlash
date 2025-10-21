package zed.rainxch.vocabularyflash.app.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import zed.rainxch.vocabularyflash.core.data.local.db.AppDatabase
import zed.rainxch.vocabularyflash.core.data.utils.Constants.DB_FILE
import java.io.File

fun getAppDatabase(): AppDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_FILE)
    return Room
        .databaseBuilder<AppDatabase>(name = dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .build()
}