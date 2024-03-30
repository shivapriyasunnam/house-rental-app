package com.example.house_rental_app.data

data class PropertyDetails(
    val imageId: Int,
    val address: String,
    val leaseAvailability: String,
    val bedrooms: Int,
    val bathrooms: Int
)
