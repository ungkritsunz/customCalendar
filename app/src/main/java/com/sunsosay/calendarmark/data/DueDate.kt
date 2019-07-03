package com.sunsosay.calendarmark.data

import android.os.Parcel
import android.os.Parcelable

data class DueDate(var day: String? = "",
                   var title: String? = "",
                   var detail: String? = "",
                   var startTime: String? = "",
                   var endTime: String? = "") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(day)
        parcel.writeString(title)
        parcel.writeString(detail)
        parcel.writeString(startTime)
        parcel.writeString(endTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DueDate> {
        override fun createFromParcel(parcel: Parcel): DueDate {
            return DueDate(parcel)
        }

        override fun newArray(size: Int): Array<DueDate?> {
            return arrayOfNulls(size)
        }
    }
}