package com.example.thecars.lists

import com.example.thecars.R

val unknown_images = listOf(
    R.drawable.unknownmodel,
    R.drawable.unknownmodel,
    R.drawable.unknownmodel,
    R.drawable.unknownmodel)

val mdx1_images = listOf(
    R.drawable.mdx1_image_front,
    R.drawable.mdx1_image_back,
    R.drawable.mdx1_image_side)

val acuraModelToImages = mapOf(
    "EL" to unknown_images,
    "ILX" to unknown_images,
    "MDX" to mdx1_images,
    "RDX" to unknown_images,
    "RSX" to unknown_images,
    "TL" to unknown_images,
    "TLX" to unknown_images,
    "TSX" to unknown_images,
    "ZDX" to unknown_images)

val acuraDatesToImages = mapOf(
    "I 2000-2006" to mdx1_images,
    "II 2006-2010" to unknown_images,
    "II restyling 2010-2013" to unknown_images,
    "III 2013-2015" to unknown_images,
    "III restyling 1 2015-2016" to unknown_images,
    "III restyling 2 2016-2020" to unknown_images,
    "IV 2021-2023" to unknown_images,)