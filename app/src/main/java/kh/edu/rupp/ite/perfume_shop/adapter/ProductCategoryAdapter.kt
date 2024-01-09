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
import kh.edu.rupp.ite.perfume_shop.databinding.ViewHolderCategoryProductsBinding
import kh.edu.rupp.ite.perfume_shop.databinding.ViewHolderHomeProductsBinding

class ProductCategoryAdapter:ListAdapter<Product , ProductCategoryAdapter.ProductViewHolder>(
    object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
) {



    class ProductViewHolder(val itemBinding: ViewHolderCategoryProductsBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bind(product: Product) {
            val imageUrl: String? = product.image?.firstOrNull()?.url

            if (imageUrl != null) {
                Log.d("url", imageUrl)
                Picasso.get().load("http://10.0.2.2:8888/images/$imageUrl").into(itemBinding.imgProduct)
            } else {
                // Handle the case where imageUrl is null (e.g., provide a default image or handle it accordingly)
                Log.e("url", "Image URL is null for product: ${product.name}")
            }
            itemBinding.productBrand.text = product.brand
            itemBinding.productName.text = product.name
        }

    }
    var onProductsClickListener: ((Int, Product) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context);
        val binding:ViewHolderCategoryProductsBinding = ViewHolderCategoryProductsBinding.inflate(layoutInflater,parent,false);
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