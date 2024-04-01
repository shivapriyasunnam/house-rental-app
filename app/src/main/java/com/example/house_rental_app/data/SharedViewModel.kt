package com.example.house_rental_app.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _userId = MutableLiveData<String?>()
    val userId: LiveData<String?> = _userId

    fun setUserId(id: String) {
        _userId.value = id
        Log.println(Log.INFO, "Setting userid", _userId.value.toString())
    }
}