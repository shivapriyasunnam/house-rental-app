package com.example.house_rental_app.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
//    @ColumnInfo(name = "houses") val housesList: List<HouseEntity>,
    @ColumnInfo(name = "emailid") val emailId: String,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
    @ColumnInfo(name = "showOnlyEmail") val showOnlyEmail: Boolean,
    @ColumnInfo(name= "showOnlyPhone") val showOnlyPhone: Boolean,


    )