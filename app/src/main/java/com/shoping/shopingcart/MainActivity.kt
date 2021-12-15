package com.shoping.shopingcart

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.reflect.TypeToken
import com.shoping.shopingcart.adapter.ProductListAdapter
import com.shoping.shopingcart.model.Products
import com.shoping.shopingcart.network.ProductHelper
import com.shoping.shopingcart.network.ProductService
import com.shoping.shopingcart.network.ViewModelFactory
import com.shoping.shopingcart.util.OnItemClick
import com.shoping.shopingcart.util.SharedPreferenceUtil
import com.shoping.shopingcart.util.Status
import com.shoping.shopingcart.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.IOException


class MainActivity : AppCompatActivity(), OnItemClick {
    lateinit var productViewModel: ProductViewModel
    lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress_bar.visibility = View.VISIBLE

        adapter = ProductListAdapter(arrayListOf(), this)
        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adapter

        productViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ProductHelper(ProductService.apiService))
        ).get(ProductViewModel::class.java)
        cartCount()

        relativeLayoutCart.setOnClickListener {
            val intent = Intent(this, CartDetails::class.java)
            startActivity(intent)
        }

        getTheProductList()



    }



    /*
    fetching product list from viewmodel
    * */
    private fun getTheProductList() {
        productViewModel.getProductList().observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        val result = it.data?.body()?.products as ArrayList<Products>
                        adapter = ProductListAdapter(result, this)
                        recycler_view.adapter = adapter

                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {


                    }
                }


            }
        })
    }


    override fun onItemClickListner(products: Products) {
        SharedPreferenceUtil.productAddToCart(products, applicationContext)
        val arrayList = SharedPreferenceUtil.getProductList(this.applicationContext)
        textViewCount.visibility = View.VISIBLE
        textViewCount.text = arrayList.size.toString()


    }

    //maintaining cart count
    fun cartCount() {
        val arrayList = SharedPreferenceUtil.getProductList(this.applicationContext)
        if (arrayList.isNullOrEmpty()) {
            textViewCount.visibility = View.INVISIBLE
        } else {
            textViewCount.visibility = View.VISIBLE
            textViewCount.text = arrayList.size.toString()
        }
    }
}