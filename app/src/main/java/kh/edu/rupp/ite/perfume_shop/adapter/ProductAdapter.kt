package kh.edu.rupp.ite.perfume_shop.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.perfume_shop.api.model.Categories
import kh.edu.rupp.ite.perfume_shop.api.model.Product
import kh.edu.rupp.ite.perfume_shop.databinding.ViewHolderHomeProductsBinding

class ProductAdapter:ListAdapter<Product , ProductAdapter.ProductViewHolder>(
    object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
) {



    class ProductViewHolder(val itemBinding: ViewHolderHomeProductsBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bind(product: Product) {
            val imageUrl: String? = product.image.firstOrNull()?.url
            Log.d("url", imageUrl.toString())

            // Check if imageUrl is not null before concatenating
            val fullImageUrl = "http://10.0.2.2:8888/images/$imageUrl"

            // Use Picasso to load the image
            if (imageUrl != null) {
                Picasso.get().load(fullImageUrl).into(itemBinding.imgProduct)
            }

            itemBinding.productBrand.text = product.brand
            itemBinding.productName.text = product.name
            itemBinding.productPrice.text = product.price.toString()
        }

    }
    var onProductsClickListener: ((Int, Product) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context);
        val binding:ViewHolderHomeProductsBinding = ViewHolderHomeProductsBinding.inflate(layoutInflater,parent,false);
        return ProductViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item:Product = getItem(position)
        holder.bind(item)
        holder.itemBinding.root.setOnClickListener{
            onProductsClickListener?.invoke(position,item)

        }

    }
}