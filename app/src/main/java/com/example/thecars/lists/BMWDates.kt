package com.example.thecars.lists

import com.example.thecars.R

val dates_bmw_m5 = listOf(
    "E39 1998-2003",
    "E60_E61 2007-2010",
    "F10 2011-2017",
    "F90 2018-NOW"
)
val images_bmw_m5 = listOf(
    R.drawable.bmw_m5_e39,
    R.drawable.bmw_m5_e60,
    R.drawable.bmw_m5_f10,
    R.drawable.bmw_m5_f90
)

val images_bmw_m5_e39 = listOf(
    R.drawable.bmw_m5_e39_front,
    R.drawable.bmw_m5_e39_back,
    R.drawable.bmw_m5_e39_side
)
val images_bmw_m5_e60 = listOf(
    R.drawable.bmw_m5_e60_front,
    R.drawable.bmw_m5_e60_back,
    R.drawable.bmw_m5_e60_side
    )
val images_bmw_m5_f10 = listOf(
    R.drawable.bmw_m5_f10_front,
    R.drawable.bmw_m5_f10_back,
    R.drawable.bmw_m5_f10_side
)
val images_bmw_m5_f90 = listOf(
    R.drawable.bmw_m5_f90_front,
    R.drawable.bmw_m5_f90_back,
    R.drawable.bmw_m5_f90_side
)

val bmwModelsToDates = mapOf(
    "M5" to dates_bmw_m5
)

val bmwM5DatesToImages = mapOf(
    "E39 1998-2003" to images_bmw_m5_e39,
    "E60_E61 2007-2010" to images_bmw_m5_e60,
    "F10 2011-2017" to images_bmw_m5_f10,
    "F90 2018-NOW" to images_bmw_m5_f90
)


val bmw_dates = mapOf(
    "M5" to dates_bmw_m5
)

val bmw_images = mapOf(
    "M5" to images_bmw_m5
)