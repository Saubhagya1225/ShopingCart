package com.shoping.shopingcart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.shoping.shopingcart.R
import com.shoping.shopingcart.model.Products
import com.shoping.shopingcart.util.OnItemClick
import com.squareup.picasso.Picasso
import java.util.*

class ProductListAdapter(var productList: ArrayList<Products>, val itemClick: OnItemClick):
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var productname: AppCompatTextView = view.findViewById(R.id.productName)
        var productTitle: AppCompatTextView = view.findViewById(R.id.productPrice)
       var productImg: AppCompatImageView = view.findViewById(R.id.productImage)
        var cardMain : CardView = view.findViewById(R.id.cardProduct)


        fun bind(products: Products) {
            productname.text = products.name
            productTitle.text = products.price
            Picasso.with(productImg.context).load(products.image_url)
                .fit().centerCrop()
                .into(productImg);
           // tvBalance.text = accountsItem.balance.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_product, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.cardMain.setOnClickListener {
            itemClick.onItemClickListner(productList[position])
        }
    }

    override fun getItemCount(): Int {
       return productList.size
    }


}