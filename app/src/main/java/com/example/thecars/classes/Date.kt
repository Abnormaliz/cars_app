package com.example.thecars.classes

import android.os.Parcel
import android.os.Parcelable

data class Date(
    val name: String,
    val previewPhoto: Int = 0,
    val frontPhoto: Int = 0,
    val sidePhoto: Int = 0,
    val backPhoto: Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(previewPhoto)
        parcel.writeInt(frontPhoto)
        parcel.writeInt(sidePhoto)
        parcel.writeInt(backPhoto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Date> {
        override fun createFromParcel(parcel: Parcel): Date {
            return Date(parcel)
        }

        override fun newArray(size: Int): Array<Date?> {
            return arrayOfNulls(size)
        }
    }
}