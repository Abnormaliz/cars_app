package com.example.thecars.classes
import android.os.Parcel
import android.os.Parcelable


data class Car(val imageId: Int, val title: String, val modelList: List<Model>) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        mutableListOf<Model>().apply {
            parcel.readList(this, Model::class.java.classLoader)
        } )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageId)
        parcel.writeString(title)
        parcel.writeList(modelList)
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