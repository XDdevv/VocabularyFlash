package zed.rainxch.vocabularyflash.app.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory
import zed.rainxch.vocabularyflash.core.data.local.db.AppDatabase
import zed.rainxch.vocabularyflash.core.data.utils.Constants.DB_FILE

fun getAppDatabase(): AppDatabase {
    val dbFile = NSHomeDirectory() + "/$DB_FILE"
    return Room.databaseBuilder(
        name = dbFile,
        factory = { AppDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}