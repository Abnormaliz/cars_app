package com.example.thecars.app.presentation.models

import android.os.Parcelable
import com.example.thecars.data.classes.Car
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarUi(
    val brand: String,
    val model: String,
    val name: String,
    val previewPhoto: Int = 0,
    val frontPhoto: Int = 0,
    val backPhoto: Int = 0,
    val sidePhoto: Int = 0
) : Parcelable

fun Car.toCarUi(): CarUi {
    return CarUi(
        brand = this.brand,
        model = this.model,
        name = this.name,
        previewPhoto = this.previewPhoto,
        frontPhoto = this.frontPhoto,
        backPhoto = this.backPhoto,
        sidePhoto = this.sidePhoto
    )
}

fun CarUi.toCar(): Car {
    return Car(
        brand = this.brand,
        model = this.model,
        name = this.name,
        previewPhoto = this.previewPhoto,
        frontPhoto = this.frontPhoto,
        backPhoto = this.backPhoto,
        sidePhoto = this.sidePhoto
    )
}

