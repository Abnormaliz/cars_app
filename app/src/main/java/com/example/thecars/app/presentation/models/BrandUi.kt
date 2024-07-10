package com.example.thecars.app.presentation.models

import android.os.Parcelable
import com.example.thecars.data.classes.Brand
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrandUi(
    val logo: Int,
    val brand: String,
    val modelList: List<ModelUi>
) : Parcelable

fun Brand.toBrandUi(): BrandUi {
    return BrandUi(
        logo = this.logo,
        brand = this.brand,
        modelList = this.modelList.map { model -> model.toModelUi() }
    )
}
