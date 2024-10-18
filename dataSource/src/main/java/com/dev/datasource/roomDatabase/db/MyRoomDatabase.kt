package com.dev.datasource.roomDatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.datasource.BuildConfig
import com.dev.datasource.model.entity.UserInfoEntity
import com.dev.datasource.roomDatabase.dao.UserInfoDao
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [
        UserInfoEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MyRoomDatabase : RoomDatabase() {


    abstract fun userInfoDao(): UserInfoDao

    companion object {

        private const val NAME = "my_db"

        @Volatile
        private var INSTANCE: MyRoomDatabase? = null

        fun getInstance(context: Context): MyRoomDatabase {
            synchronized(this) {
                val userEnteredPassphrase = BuildConfig.Passphrase.toCharArray()
                val passphrase: ByteArray = SQLiteDatabase.getBytes(userEnteredPassphrase)
                val factory = SupportFactory(passphrase)
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        MyRoomDatabase::class.java,
                        NAME
                    ).apply {
                        if (!BuildConfig.DEBUG) {
                            openHelperFactory(factory)
                        }
                    }
                        .build()
                }
                return instance
            }
        }
    }
}
