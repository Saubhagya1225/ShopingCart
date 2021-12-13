package com.shoping.shopingcart.network

class ProductHelper(private val apiService:ProductApi) {
    suspend fun getProductList() = apiService.getProductList()
}