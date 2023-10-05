package com.example.thecars
val unknown_images = listOf(
    R.drawable.unknownmodel,
    R.drawable.unknownmodel,
    R.drawable.unknownmodel,
    R.drawable.unknownmodel)

val mdx1_images = listOf(
    R.drawable.mdx1_image_front,
    R.drawable.mdx1_image_back,
    R.drawable.mdx1_image_side)

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
    "I" to mdx1_images,
    "II" to unknown_images,
    "IIrest" to unknown_images,
    "III" to unknown_images,
    "IIIrest1" to unknown_images,
    "IIIrest2" to unknown_images,
    "IV" to unknown_images,
)