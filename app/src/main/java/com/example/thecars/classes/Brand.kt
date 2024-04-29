package com.example.thecars.classes

import android.os.Parcel
import android.os.Parcelable


data class Brand(
    val logo: Int,
    val name: String,
    val modelList: List<Model>) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        mutableListOf<Model>().apply {
            parcel.readList(this, Model::class.java.classLoader)
        })

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(logo)
        parcel.writeString(name)
        parcel.writeList(modelList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Brand> {
        override fun createFromParcel(parcel: Parcel): Brand {
            return Brand(parcel)
        }

        override fun newArray(size: Int): Array<Brand?> {
            return arrayOfNulls(size)
        }
    }
}