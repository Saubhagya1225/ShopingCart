package com.shoping.shopingcart.model

import java.io.Serializable

data class Products(
    val image_url: String,
    val name: String,
    val price: String,
    val rating: Int
) : Serializable