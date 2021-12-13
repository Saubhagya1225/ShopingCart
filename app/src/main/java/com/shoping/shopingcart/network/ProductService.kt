package com.shoping.shopingcart.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductService {
    private val BASE_URL = "https://my-json-server.typicode.com/"

    fun getProductService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val apiService: ProductApi = getProductService().create(ProductApi::class.java)

}