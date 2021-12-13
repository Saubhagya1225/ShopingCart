package com.shoping.shopingcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.shoping.shopingcart.model.Products
import com.shoping.shopingcart.util.Resource
import kotlinx.coroutines.Dispatchers

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    fun getProductList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getProductList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}