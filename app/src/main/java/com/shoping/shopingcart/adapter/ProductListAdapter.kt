package com.shoping.shopingcart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shoping.shopingcart.R
import com.shoping.shopingcart.model.Products
import com.shoping.shopingcart.util.OnItemClick
import java.util.*

class ProductListAdapter(var productList: ArrayList<Products>, val itemClick: OnItemClick) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var productname: AppCompatTextView = view.findViewById(R.id.productName)
        var productTitle: AppCompatTextView = view.findViewById(R.id.productPrice)
        var productImg: AppCompatImageView = view.findViewById(R.id.productImage)
        var ratingbar: AppCompatRatingBar = view.findViewById(R.id.ratingbar)
        var cardMain: CardView = view.findViewById(R.id.cardProduct)
        var addtext: AppCompatTextView = view.findViewById(R.id.addtocarttext)


        fun bind(products: Products) {
            productname.text = products.name
            productTitle.text = products.price
            ratingbar.rating = products.rating.toFloat()

            Glide.with(productImg.context)
                .load(products.image_url)
                .into(productImg);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_product, parent, false)
    )

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.addtext.setOnClickListener {
            itemClick.onItemClickListner(productList[position])
            holder.addtext.text = "IN CART"
            holder.addtext.setTextColor(
                AppCompatResources.getColorStateList(
                    context!!,
                    R.color.green
                )
            )
            holder.addtext.setClickable(false)

        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }


}