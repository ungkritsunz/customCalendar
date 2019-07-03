package com.sunsosay.calendarmark.main

import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sunsosay.calendarmark.R
import com.sunsosay.calendarmark.data.CalendarData
import com.sunsosay.calendarmark.databinding.ActivityMainBinding


class MainViewModel : ViewModel() {
    var binding: ActivityMainBinding? = null
    val TAG = "logcalenda"

    fun getBinding(activity: MainActivity): ActivityMainBinding {
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_main)
        return binding!!
    }

    fun addData() {
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("DueDate")
        myRef.push().setValue(CalendarData("12", "19:22"))
        myRef.push().setValue(CalendarData("13", "20:33"))
        myRef.push().setValue(CalendarData("14", "21:44"))
    }

    fun callData() {
        // Read from the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("ScheduleCalendar")
//        myRef.orderByKey().limitToFirst(3)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value
                Log.d(TAG, "Value is: " + value!!)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }



}