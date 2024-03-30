package com.example.house_rental_app.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.house_rental_app.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * from user_table WHERE emailid = :emailId AND password = :password LIMIT 1")
    fun userLogin(emailId: String, password: String): UserEntity? // Change return type

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser(user: UserEntity)

}