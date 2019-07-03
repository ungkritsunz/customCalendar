package com.sunsosay.calendarmark.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.marcohc.robotocalendar.RobotoCalendarView
import com.sunsosay.calendarmark.data.DueDate
import com.sunsosay.calendarmark.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(), RobotoCalendarView.RobotoCalendarListener {

    var binding: ActivityMainBinding? = null
    private val viewModel = MainViewModel()
    var arrList = ArrayList<DueDate>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Gets the calendar from the view
        binding = viewModel.getBinding(this)
        // Set listener, in this case, the same activity
        binding?.robotoCalendarPicker!!.setRobotoCalendarListener(this)
        binding?.robotoCalendarPicker!!.setShortWeekDays(false)
        binding?.robotoCalendarPicker!!.showDateTitle(true)
        binding?.robotoCalendarPicker!!.date = Date()
        //

//        viewModel.addData()
//        testFireStore()
//        readData()
        var b = DueDate("13", "event meeting", "introduced limited compatibility support for vector", "16.00", "18.00")
        var d = DueDate("13", "DLDA meeting", "introduced limited compatibility support for vector", "16.00", "18.00")
        var e = DueDate("18", "SECTION38 meeting", "introduced limited compatibility support for vector", "16.00", "18.00")
        var f = DueDate("25", "IOS meeting", "introduced limited compatibility support for vector", "16.00", "18.00")
        var a = DueDate("28", "PIBIC meeting", "I have a Date object in Java stored as Java's Date type.", "18.00", "20.00")
        var c = DueDate(
            "9",
            "dld meeting",
            " it's a support library, so you can use it with all Android platform versions",
            "10.00",
            "11.00"
        )
        arrList.add(a)
        arrList.add(b)
        arrList.add(c)
        arrList.add(d)
        arrList.add(e)
        arrList.add(f)
        arrList
//        MainAdapter()

        arrList.forEach {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, it.day!!.toInt())

            val random = Random(System.currentTimeMillis())
            val style = random.nextInt(2)
            if (style == 0) {
                binding?.robotoCalendarPicker!!.markCircleImage1(calendar.time)
            } else {
                binding?.robotoCalendarPicker!!.markCircleImage2(calendar.time)
            }
        }

    }


    fun testFireStore() {
        val db = FirebaseFirestore.getInstance()
        // Create a new user with a first and last name
//        val user = HashMap<String, Any>()
//        user["day"] = "12"
//        user["title"] = "SEMINAR IO 2019"
//        user["detail"] = "this message for test calendar app."
        var dataAdd = DueDate("13", "SEMINAR IO 2019", "this message for test calendar app.")
        // Add a new document with a generated ID
        db.collection("dueDate")
            .add(dataAdd)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "DocumentSnapshot added with ID: " + documentReference.id, Toast.LENGTH_LONG)
                    .show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Fail", Toast.LENGTH_LONG)
                    .show()
            }
    }

    private fun readData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("dueDate")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        document.data
                        Log.d("readdata", document.id + " => " + document.data)
                    }
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG)
                        .show()
                }
            })
    }

    override fun onDayClick(date: Date) {
        var cal = Calendar.getInstance()
        cal.setTime(date)
        var day = cal.get(Calendar.DAY_OF_MONTH)
        var list = ArrayList<DueDate>()
        arrList.forEach {
            if (it.day == day.toString()) {
                list.add(it)
            }
        }
        if (list.size > 0) {
            binding?.recycleActivitylicenselistListLicense?.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MainAdapter().apply {
                    this.dataVerifyLicense = list
                }
            }
        }else{
            binding?.recycleActivitylicenselistListLicense?.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MainAdapter().apply {
                    this.dataVerifyLicense = list
                }
            }
        }
//        Toast.makeText(this, "onDayClick: $date", Toast.LENGTH_SHORT).show()
//        binding?.tvShowevent?.text = date.time.toString()
    }

    override fun onDayLongClick(date: Date) {
        Toast.makeText(this, "onDayLongClick: $date", Toast.LENGTH_SHORT).show()
    }

    override fun onRightButtonClick() {
        Toast.makeText(this, "onRightButtonClick!", Toast.LENGTH_SHORT).show()
    }

    override fun onLeftButtonClick() {
        Toast.makeText(this, "onLeftButtonClick!", Toast.LENGTH_SHORT).show()
    }

    fun setDateSchedule() {
        val calendar = Calendar.getInstance()
        var num = listOf(1, 2, 3, 4, 5)
        num.forEach { num ->
            calendar.set(Calendar.DAY_OF_MONTH, num)
            binding?.robotoCalendarPicker!!.markCircleImage1(calendar.time)
        }
    }

    fun mockLogic() {
        //        viewModel.callData()
//        setDateSchedule()

//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.DAY_OF_MONTH, 10)
//
//        when (0) {
//            0 -> {
//                binding?.robotoCalendarPicker!!.markCircleImage1(calendar.time)
//                calendar.set(Calendar.DAY_OF_MONTH, 11)
//                binding?.robotoCalendarPicker!!.markCircleImage1(calendar.time)
//                calendar.set(Calendar.DAY_OF_MONTH, 12)
//                binding?.robotoCalendarPicker!!.markCircleImage2(calendar.time)
//
//            }
//            1 -> binding?.robotoCalendarPicker!!.markCircleImage2(calendar.time)
//            else -> {
//            }
//        }

//        robotoCalendarView = findViewById(R.id.robotoCalendarPicker)
//        val markDayButton = findViewById(R.id.markDayButton)
//        val clearSelectedDayButton = findViewById(R.id.clearSelectedDayButton)

//        markDayButton.setOnClickListener { view ->
//            val calendar = Calendar.getInstance()
//            val random = Random(System.currentTimeMillis())
//            val style = random.nextInt(2)
//            val daySelected = random.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
//            calendar.set(Calendar.DAY_OF_MONTH, daySelected)

//            viewModel.addData()
//            viewModel.callData()
//
//            val calendar = Calendar.getInstance()
//            calendar.set(Calendar.DAY_OF_MONTH, 26)
//
//            when (0) {
//                0 -> {
//                    binding?.robotoCalendarPicker!!.markCircleImage1(calendar.time)
//                    calendar.set(Calendar.DAY_OF_MONTH, 24)
//                    binding?.robotoCalendarPicker!!.markCircleImage1(calendar.time)
//                    calendar.set(Calendar.DAY_OF_MONTH, 25)
//                    binding?.robotoCalendarPicker!!.markCircleImage2(calendar.time)
//
//                }
//                1 -> binding?.robotoCalendarPicker!!.markCircleImage2(calendar.time)
//                else -> {
//                }
//            }
//        }
//
//        clearSelectedDayButton.setOnClickListener { v -> binding?.robotoCalendarPicker!!.clearSelectedDay() }
//

    }
}