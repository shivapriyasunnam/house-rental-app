package com.example.house_rental_app

import android.app.Application



class DatabaseApplication : Application() {
    // You can add application-wide initialization or state management here

    companion object {
        private lateinit var instance: DatabaseApplication
        lateinit var container: AppContainer
        fun getInstance(): DatabaseApplication {
            return instance
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        container = AppDataContainer(this)
        // Perform any initialization here
    }
}