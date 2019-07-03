package com.sunsosay.calendarmark.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class CalendarData(
    var day: String? = "",
    var time: String? = ""
)