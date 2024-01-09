package kh.edu.rupp.ite.perfume_shop.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.perfume_shop.api.model.Product
import kh.edu.rupp.ite.perfume_shop.databinding.ViewHolderShoppingBagBinding

class ShoppingBagAdapter:ListAdapter<Product , ShoppingBagAdapter.ShoppingViewHolder>(
    object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
) {


    class ShoppingViewHolder(val itemBinding: ViewHolderShoppingBagBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(product: Product){
            itemBinding.productBrand.text = product.brand
            itemBinding.price.text = product.price.toString()
            itemBinding.quantity.text = product.count.toString()
            val imageUrl: String? = product.image.firstOrNull()?.url
            Log.d("url", imageUrl.toString())

            // Check if imageUrl is not null before concatenating
            val fullImageUrl = "http://10.0.2.2:8888/images/$imageUrl"

            // Use Picasso to load the image
            if (imageUrl != null) {
                Picasso.get().load(fullImageUrl).into(itemBinding.imgProduct)
            }

        }
    }

    var onPayClickListener: (() -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val binding:ViewHolderShoppingBagBinding = ViewHolderShoppingBagBinding.inflate(layoutInflater,parent,false)
        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val item:Product = getItem (position)
        holder.bind(item)
    }
}

