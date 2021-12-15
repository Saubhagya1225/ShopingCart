package com.shoping.shopingcart.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Products(
    @NonNull
    @PrimaryKey
    val id: Int,
    val image_url: String,
    val name: String,
    val price: String,
    val rating: Int
) : Serializable