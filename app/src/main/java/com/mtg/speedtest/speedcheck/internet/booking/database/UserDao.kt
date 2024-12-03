package com.mtg.speedtest.speedcheck.internet.booking.database

import androidx.room.*
import com.mtg.speedtest.speedcheck.internet.booking.model.User

@Dao
interface UserDao {
    @Query("Select * from use_table")
    suspend fun getListUsers(): MutableList<User>

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

}