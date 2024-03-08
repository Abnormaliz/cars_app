package com.example.thecars.lists

import com.example.thecars.R

val dates_acura_mdx: List<String> = listOf(
    "I 2000-2006",
    "II 2006-2010",
    "II restyling 2010-2013",
    "III 2013-2015",
    "III restyling 1 2015-2016",
    "III restyling 2 2016-2020",
    "IV 2021-2023")

val images_acura_mdx: List<Int> = listOf(
    R.drawable.mdx1,
    R.drawable.mdx2,
    R.drawable.mdx2rest,
    R.drawable.mdx3,
    R.drawable.mdx3rest1,
    R.drawable.mdx3rest2,
    R.drawable.mdx4)

val mdx1_images = listOf(
    R.drawable.mdx1_image_front,
    R.drawable.mdx1_image_back,
    R.drawable.mdx1_image_side)

val acuraModelToDates = mapOf(
    "MDX" to dates_acura_mdx
)

val acuraMdxDatesToImages = mapOf(
    "I 2000-2006" to mdx1_images
)

val acura_dates = mapOf(
    "MDX" to dates_acura_mdx
)

val acura_images = mapOf(
    "MDX" to images_acura_mdx
)






