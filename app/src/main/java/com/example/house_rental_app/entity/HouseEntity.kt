package com.example.house_rental_app.entity

import android.provider.MediaStore.Images
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "house_table",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["ownerId"],
        onDelete = ForeignKey.CASCADE // Define onDelete behavior as needed
    )])
data class HouseEntity(

    @PrimaryKey(autoGenerate = true) val houseId: Int = 0,
    @ColumnInfo(name = "ownerId") val ownerId: Int,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name= "location") val location: String,
    @ColumnInfo(name = "images") val images: String,
    @ColumnInfo(name = "utilities") val utilities: String,
    @ColumnInfo(name = "description") val description: String,
)