package com.example.thecars.classes

import android.os.Parcel
import android.os.Parcelable

data class Car(
    val brand: String,
    val model: String,
    val name: String,
    val previewPhoto: Int = 0,
    val frontPhoto: Int = 0,
    val backPhoto: Int = 0,
    val sidePhoto: Int = 0,
    var isFavorite: Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(brand)
        parcel.writeString(name)
        parcel.writeString(model)
        parcel.writeInt(previewPhoto)
        parcel.writeInt(frontPhoto)
        parcel.writeInt(backPhoto)
        parcel.writeInt(sidePhoto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }
}