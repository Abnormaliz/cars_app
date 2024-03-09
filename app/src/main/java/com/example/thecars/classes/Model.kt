package com.example.thecars.classes

import com.example.thecars.lists.dates_bmw_m5

data class Model(val name: String, val list: List<Date>) {

}

val models = listOf(
    Model("M5",  dates_bmw_m5)
)