package com.example.thecars.app.presentation.models

import android.os.Parcelable
import com.example.thecars.domain.models.classes.Model
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelUi(
    val name: String,
    val list: List<CarUi>
) : Parcelable

fun Model.toModelUi(): ModelUi {
    return ModelUi(
        name = this.name,
        list = this.list.map { car -> car.toCarUi() }
    )
}