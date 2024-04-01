package com.example.house_rental_app.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user_table",
    indices = [Index(value = ["emailid"], unique = true)]
)
data class UserEntity (

    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "emailid") var emailId: String,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String,
    @ColumnInfo(name = "showOnlyEmail") val showOnlyEmail: Boolean,
    @ColumnInfo(name= "showOnlyPhone") val showOnlyPhone: Boolean,


    )