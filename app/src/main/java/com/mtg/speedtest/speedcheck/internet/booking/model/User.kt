package com.mtg.speedtest.speedcheck.internet.booking.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "use_table")
data class User(
    @PrimaryKey(autoGenerate = false) val emailUser: String,
    @ColumnInfo var firstUser: String?,
    @ColumnInfo var lastUser: String?,
    @ColumnInfo var passwordUser: String?

) : Serializable