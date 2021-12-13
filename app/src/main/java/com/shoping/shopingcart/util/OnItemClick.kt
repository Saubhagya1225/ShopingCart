package com.shoping.shopingcart.util

import com.shoping.shopingcart.model.Products

interface OnItemClick {
    fun onItemClickListner(products: Products)
}