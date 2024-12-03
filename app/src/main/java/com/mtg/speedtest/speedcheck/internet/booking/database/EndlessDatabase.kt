package com.mtg.speedtest.speedcheck.internet.booking.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.model.User

@Database(entities = [User::class], version = 1)
abstract class EndlessDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: EndlessDatabase? = null

        fun getInstance(context: Context): EndlessDatabase? {
            if (instance == null) {
                synchronized(EndlessDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EndlessDatabase::class.java,
                        CommonUtils.nameDatabase
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}