package com.example.thecars.domain.models.classes

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//domain
data class Brand(
    val logo: Int,
    val name: String,
    val modelList: List<Model>
)


//presentation
@Parcelize
data class BrandUi(
    val logo: Int,
    val name: String,
    val modelList: List<ModelUi>) : Parcelable

fun Brand.toBrandUi(): BrandUi {
    return BrandUi(
        logo = this.logo,
        name = this.name,
        modelList = this.modelList
    )
}

object BrandMapper {
    fun toBrandUi(brand: Brand): BrandUi {
        return BrandUi(
            logo = brand.logo,
            name = brand.name,
            modelList = brand.modelList
        )
    }

    fun toBrand(brand: BrandUi): Brand {

    }
}
