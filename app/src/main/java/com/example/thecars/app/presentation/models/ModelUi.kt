package com.example.thecars.app.presentation.models

import android.os.Parcelable
import com.example.thecars.data.classes.Model
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelUi(
    val brand: String,
    val model: String,
    val list: List<CarUi>
) : Parcelable

fun Model.toModelUi(): ModelUi {
    return ModelUi(
        brand = this.brand,
        model = this.model,
        list = this.list.map { car -> car.toCarUi() }
    )
}