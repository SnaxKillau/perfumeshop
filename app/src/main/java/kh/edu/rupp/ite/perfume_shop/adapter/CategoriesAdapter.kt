package kh.edu.rupp.ite.perfume_shop.adapter
import android.util.Log
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import kh.edu.rupp.ite.perfume_shop.api.model.Categories
import android.view.LayoutInflater
import kh.edu.rupp.ite.perfume_shop.databinding.ViewHolderCategoriesBinding



class CategoriesAdapter : ListAdapter<Categories, CategoriesAdapter.CategoriesViewHolder>(
    object : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem.id== newItem.id
        }
    }
) {
    var onCategoriesClickListener: ((Int , Categories) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCategoriesBinding.inflate(layoutInflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        Log.d("data" , "onBindViewHolder")

        holder.itemBinding.btnCategories.setOnClickListener{
            onCategoriesClickListener?.invoke(position,item)
            Log.d("data item" , item.id.toString())
        }
    }

    inner class CategoriesViewHolder(val itemBinding: ViewHolderCategoriesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(categories: Categories) {
            itemBinding.btnCategories.text = categories.name
        }
    }
}
