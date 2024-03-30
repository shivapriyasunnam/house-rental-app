package com.example.house_rental_app.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.house_rental_app.DatabaseApplication
import com.example.house_rental_app.entity.UserEntity
import com.example.house_rental_app.models.User
import com.example.house_rental_app.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository: UserRepository by lazy {
        DatabaseApplication.container.userRepository
    }
//    * Holds the current user registration/login state.
//    */
    var userUiState by mutableStateOf(UserUiState())
        private set

    /**
     * Updates the [userUiState] with the value provided in the argument.
     * This method also triggers validation for input values.
     */
    fun updateUserUiState(userDetails: UserDetails) {
        userUiState = UserUiState(
            userDetails = userDetails,
            isEntryValid = validateInput(userDetails)
        )
    }

    /**
     * Registers the user if the input is valid.
     */
    suspend fun registerUser(user: UserEntity) {
        Log.println(Log.INFO, "User register", user.toString() )
//        if (validateInput()) {
        viewModelScope.launch {
            userRepository.registerUser(user)
        }
//        }
    }

    /**
     * Logs in the user if the input is valid.
     */
    suspend fun loginUser(email: String, password: String): UserEntity? {
//        if (validateInput()) {
            // Logic to handle user login
        viewModelScope.launch {
            Log.println(Log.INFO, "Modda Login aindu", "Noice")
            val user = userRepository.userLogin(email, password)
            Log.println(Log.INFO, "Modda Login aindu", user.toString())
        }
//        }
        return null
    }

    /**
     * Validates the input fields.
     */
    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            username.isNotBlank() && password.isNotBlank() && email.isNotBlank()
        }
    }
}

/**
 * Represents UI State for a User.
 */
data class UserUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)

/**
 * Represents details of a User.
 */
data class UserDetails(
    val username: String = "",
    val email: String = "",
    val password: String = ""
)

/**
 * Extension function to convert [UserDetails] to [User].
 */
fun UserDetails.toUser(): UserEntity = UserEntity(
    username = username,
    emailId = email,
    password = password,
    id=0,
    phoneNumber = "",
    showOnlyEmail = true,
    showOnlyPhone = true
)