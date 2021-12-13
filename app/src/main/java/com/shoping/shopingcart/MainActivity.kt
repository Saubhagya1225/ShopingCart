package com.shoping.shopingcart

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
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
import com.shoping.shopingcart.util.Status
import com.shoping.shopingcart.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.toolbar.*
import java.io.IOException


class MainActivity : AppCompatActivity(), OnItemClick {
   lateinit var toolbar: Toolbar
    lateinit var productViewModel: ProductViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProductListAdapter
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE
        adapter = ProductListAdapter(arrayListOf(), this)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        productViewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ProductHelper(ProductService.apiService))
        ).get(ProductViewModel::class.java)

        productViewModel.getProductList().observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        val result = it.data?.body()?.products as ArrayList<Products>
                        adapter = ProductListAdapter(result, this)
                        recyclerView.adapter = adapter

                        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {

                    }
                }


            }
        })

    }


    override fun onItemClickListner(products: Products) {
        textViewCount.visibility = View.VISIBLE
        textViewCount.text

    }
}