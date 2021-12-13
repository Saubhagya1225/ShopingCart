package com.shoping.shopingcart.viewmodel

import com.shoping.shopingcart.network.ProductHelper

class ProductRepository(private val apiHelper: ProductHelper) {
    suspend fun getProductList() = apiHelper.getProductList()
}