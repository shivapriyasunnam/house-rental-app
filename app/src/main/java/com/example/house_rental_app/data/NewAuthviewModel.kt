package com.example.house_rental_app.data


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.house_rental_app.room.User
import com.example.house_rental_app.room.UserDao
import com.example.house_rental_app.room.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewAuthViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao: UserDao

    init {
        val userDatabase = UserDatabase.getDatabase(application)
        userDao = userDatabase.userDao()
    }

    fun registerUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newUser = User(email = email, password = password)
                userDao.registerUser(newUser)
                withContext(Dispatchers.Main) {
                    onResult(true)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(false)
                }
            }
        }
    }

    fun loginUser(email: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = userDao.loginUser(email, password)
                withContext(Dispatchers.Main) {
                    onResult(user)
                }
            } catch (e: Exception) {
                Log.e("LoginUser", "No user found!", e)
                withContext(Dispatchers.Main) {
                    onResult(null)
                }
            }
        }
    }
}
