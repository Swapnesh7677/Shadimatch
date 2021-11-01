package com.triologic.jewel_cliq.room

import android.content.Context
import androidx.room.*
import com.swapnesh.shadimatcher.model.UserListResponce
import com.swapnesh.shadimatcher.model.UserRoom


@Database(entities = arrayOf(UserRoom::class), version = 1, exportSchema = false)
abstract class ListDatabase : RoomDatabase() {

    abstract fun UserDao(): DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: ListDatabase? = null

        fun getDataseClient(context: Context): ListDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, ListDatabase::class.java, "LIST_DATABASE")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE!!

            }
        }

    }

}