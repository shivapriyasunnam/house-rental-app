//package com.example.house_rental_app.viewModel
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.house_rental_app.entity.UserEntity
//import com.example.house_rental_app.repository.UserRepository
//import kotlinx.coroutines.launch
//
//class UserViewModel(private val userRepository :UserRepository) : ViewModel() {
//
////    private val userRepository :UserRepository
//
//    fun registerUser(user: UserEntity) {
//        viewModelScope.launch {
//            userRepository.registerUser(user)
//        }
//    }
//    fun userLogin(emailId: String, password: String) {
//        viewModelScope.launch {
//            userRepository.userLogin(emailId, password)
//        }
//    }
//    // You can define other functions for user-related operations here
//}