package com.example.thecars.classes

import android.os.Parcel
import android.os.Parcelable

data class Model(
    val name: String,
    val list: List<Car>) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        mutableListOf<Car>().apply {
            parcel.readList(this, Car::class.java.classLoader)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Model> {
        override fun createFromParcel(parcel: Parcel): Model {
            return Model(parcel)
        }

        override fun newArray(size: Int): Array<Model?> {
            return arrayOfNulls(size)
        }
    }

}