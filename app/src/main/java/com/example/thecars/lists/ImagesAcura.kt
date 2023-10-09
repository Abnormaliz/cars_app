package com.example.thecars.lists

import com.example.thecars.R

val unknown_images = listOf(
    R.drawable.unknownmodel,
    R.drawable.unknownmodel,
    R.drawable.unknownmodel,
    R.drawable.unknownmodel
)

val mdx1_images = listOf(
    R.drawable.mdx1_image_front,
    R.drawable.mdx1_image_back,
    R.drawable.mdx1_image_side
)

val modelToImages = mapOf(
    "EL" to unknown_images,
    "ILX" to unknown_images,
    "MDX" to mdx1_images,
    "RDX" to unknown_images,
    "RSX" to unknown_images,
    "TL" to unknown_images,
    "TLX" to unknown_images,
    "TSX" to unknown_images,
    "ZDX" to unknown_images
)

val datesToImages = mapOf(
    "I 2000-2006" to mdx1_images,
    "II 2006-2010" to unknown_images,
    "IIrest" to unknown_images,
    "III" to unknown_images,
    "IIIrest1" to unknown_images,
    "IIIrest2" to unknown_images,
    "IV" to unknown_images,
)