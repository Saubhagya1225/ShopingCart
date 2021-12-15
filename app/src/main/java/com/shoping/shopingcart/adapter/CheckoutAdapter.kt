package com.shoping.shopingcart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shoping.shopingcart.R
import com.shoping.shopingcart.model.Products
import java.util.ArrayList

class CheckoutAdapter(var productList: ArrayList<Products>) : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productname: AppCompatTextView = view.findViewById(R.id.productName)
        var productTitle: AppCompatTextView = view.findViewById(R.id.productPrice)
        var productImg: AppCompatImageView = view.findViewById(R.id.productImage)
        var ratingbar: AppCompatRatingBar = view.findViewById(R.id.ratingbar)

        fun bind(products: Products) {
            productname.text = products.name
            productTitle.text = products.price
            ratingbar.rating = products.rating.toFloat()

            Glide.with(productImg.context)
                .load(products.image_url)
                .into(productImg);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.check_out_list, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}