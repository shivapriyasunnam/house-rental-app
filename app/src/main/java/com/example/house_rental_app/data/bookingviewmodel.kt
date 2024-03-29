package com.example.house_rental_app.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.house_rental_app.models.Booking
import com.example.house_rental_app.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class bookingviewmodel(var navController: NavHostController, var context: Context) {
    var authRepository: AuthViewModel
    var progress: ProgressDialog


    init {
        authRepository = AuthViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun saveBooking(name: String, sizeofthehouse: String, locationofthehouse: String) {
        var id = System.currentTimeMillis().toString()
        var bookingData = Booking(name, sizeofthehouse, locationofthehouse, id)
        var bookingRef = FirebaseDatabase.getInstance().getReference()
            .child("Booking/$id")
        progress.show()
        bookingRef.setValue(bookingData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun viewbookings(
        booking: MutableState<Booking>,
        bookings: SnapshotStateList<Booking>
    ): SnapshotStateList<Booking> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Booking")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                bookings.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Booking::class.java)
                    booking.value = value!!
                    bookings.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return bookings
    }

    fun deleteBooking(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Bookings/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Booking deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateBooking(name: String, sizeofthehousehouse: String, locationofthehouse: String, id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Bookings/$id")
        progress.show()
        var updateData = Booking(name, sizeofthehousehouse, locationofthehouse, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}