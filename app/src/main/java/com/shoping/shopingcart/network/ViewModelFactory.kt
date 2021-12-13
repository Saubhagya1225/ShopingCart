package com.shoping.shopingcart.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoping.shopingcart.viewmodel.ProductRepository
import com.shoping.shopingcart.viewmodel.ProductViewModel

class ViewModelFactory(private var apiHelper: ProductHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(ProductRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}