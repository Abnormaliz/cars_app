package com.example.thecars.data.classes

data class RemoteCar(
    val id: Int,
    val year: Int,
    val make: String,
    val model: String,
    val type: String
)

data class CutCar(
    val brand: String,
    val model: String,
    val release: String,
    val type: String
)
fun RemoteCar.toCutCar(): CutCar {
    return CutCar(
        brand = this.make,
        model = this.model,
        release = this.year.toString(),
        type = this.type
    )
}
