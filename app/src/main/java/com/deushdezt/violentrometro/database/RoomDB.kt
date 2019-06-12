package com.deushdezt.violentrometro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deushdezt.violentrometro.database.daos.TestCaseDao
import com.deushdezt.violentrometro.database.entities.TestCase

@Database(entities = [TestCase::class], version = 2, exportSchema = false)
abstract class RoomDB: RoomDatabase() {

    abstract fun testCaseDao(): TestCaseDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(
            context: Context
        ): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, RoomDB::class.java, "Repo_Database")
                    .build()
                INSTANCE=instance
                return instance
            }

        }

    }

}