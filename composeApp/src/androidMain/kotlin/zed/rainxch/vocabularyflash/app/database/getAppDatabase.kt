package zed.rainxch.vocabularyflash.app.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import zed.rainxch.vocabularyflash.core.data.local.db.AppDatabase
import zed.rainxch.vocabularyflash.core.data.utils.Constants.DB_FILE

fun getAppDatabase(context: Context): AppDatabase {
    val dbFile = context.getDatabasePath(DB_FILE)
    return Room
        .databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
        .setDriver(BundledSQLiteDriver())
        .build()
}