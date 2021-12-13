package com.shoping.shopingcart.network

import com.shoping.shopingcart.model.ProductObj
import com.shoping.shopingcart.model.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("nancymadan/assignment/db")
    suspend fun getProductList(): Response<ProductObj>
}