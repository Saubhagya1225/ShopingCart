package com.shoping.shopingcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shoping.shopingcart.adapter.CheckoutAdapter
import com.shoping.shopingcart.adapter.ProductListAdapter
import com.shoping.shopingcart.model.Products
import com.shoping.shopingcart.util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.activity_cart_details.*

class CartDetails : AppCompatActivity() {
    lateinit var checkoutAdapter: CheckoutAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_details)


        /*
        get The Product list from local storage
        * */

        val arrayList = SharedPreferenceUtil.getProductList(this.applicationContext)
        if (arrayList.isNullOrEmpty()) {
            emptyMessage.visibility = View.VISIBLE
            checkoutView.visibility = View.INVISIBLE
        }

        checkoutAdapter = CheckoutAdapter(arrayList)
        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = checkoutAdapter

        checkoutView.setOnClickListener {
            SharedPreferenceUtil.clear(this)
            val intent = Intent(this, ConfirmationPage::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }
    }
}