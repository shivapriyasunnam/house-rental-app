package com.example.house_rental_app.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.house_rental_app.DatabaseApplication
import com.example.house_rental_app.entity.UserEntity
import com.example.house_rental_app.models.User
import com.example.house_rental_app.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _currentUser = MutableLiveData<Int>()
    val currentUser: LiveData<Int> = _currentUser
    fun setUser(user: Int){
        _currentUser.value = user
    }
    private val _userDetails = MutableLiveData<UserEntity?>()
    val userDetails: LiveData<UserEntity?> = _userDetails

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
    suspend fun loginUser(email: String, password: String): Int {
//        if (validateInput()) {
            // Logic to handle user login
        var user = 0
        viewModelScope.launch {
            try{
                user = userRepository.userLogin(email, password)
                _currentUser.postValue(user) // Use postValue for thread-safety
                _currentUser.value = user
                Log.println(Log.INFO, "Yo", user.toString())
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error logging in", e)
                _currentUser.postValue(null)
            }

        }
//        }
        return user
    }
    fun fetchUserById(userId: Int) {
        viewModelScope.launch {
            try {
                val user = userRepository.fetchUserById(userId) // Assuming this method exists
                _userDetails.postValue(user) // Assuming UserEntity includes the user ID
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching user", e)
                _userDetails.postValue(null)
            }
        }
    }

    /**
     * Validates the input fields.
     */
    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            username.isNotBlank() && password.isNotBlank() && email.isNotBlank()
        }
    }
    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.updateUser(user)
            // Optionally post the updated user ID or trigger a UI state change
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