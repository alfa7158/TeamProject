package com.example.e_commerce.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.e_commerce.R
import com.example.e_commerce.model.products.Product
import com.example.e_commerce.view.main.ProductsViewModel
import com.squareup.picasso.Picasso
import java.util.*

class ProductRecyclerViewAdapter(val viewMode:ProductsViewModel) :
    RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    val DIF_CALLBACK = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductRecyclerViewAdapter.ProductViewHolder {

        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.titleTextView.text = item.title
        holder.priceTextView.text = "${item.price} SAR"
        holder.favoriteToggleButton.isChecked = item.isFavorite
        Picasso.get().load(item.imagePath).into(holder.productImage)
        holder.favoriteToggleButton.setOnClickListener{
            if(holder.favoriteToggleButton.isChecked){
                viewMode.addFavoriteProduct(item.id)
            }else{
                viewMode.removeFavoriteProducts(item.id)
            }

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list:List<Product>){
        differ.submitList(list)

    }


    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView:TextView = itemView.findViewById(R.id.product_title_textview)
        val priceTextView:TextView = itemView.findViewById(R.id.product_price_textview)
        val productImage:ImageView = itemView.findViewById(R.id.product_imageview)
        val favoriteToggleButton:ToggleButton = itemView.findViewById(R.id.favorite_toggle_button)
    }

}