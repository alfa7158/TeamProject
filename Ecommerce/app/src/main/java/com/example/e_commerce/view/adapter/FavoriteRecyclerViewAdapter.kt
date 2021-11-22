package com.example.e_commerce.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FavoriteItemLayoutBinding
import com.example.e_commerce.databinding.FragmentProfileBinding
import com.example.e_commerce.model.products.Product
import com.example.e_commerce.view.main.FavoriteViewModel
import com.squareup.picasso.Picasso

class FavoriteRecyclerViewAdapter() :
    RecyclerView.Adapter<FavoriteRecyclerViewAdapter.FavoriteViewHolder>() {

    val DIFF_CALLBACK = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem ==newItem
        }

    }
    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)

    fun submitList(list:List<Product>){
        differ.submitList(list)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteRecyclerViewAdapter.FavoriteViewHolder {
        val binding = FavoriteItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    class FavoriteViewHolder(val binding: FavoriteItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Product){
            binding.favoriteNameTextView.text = item.title
            binding.favoritePriceTextView.text = "${item.price}SAR"

            Picasso.get().load(item.imagePath).into(binding.favortieImageview)
        }
    }

}