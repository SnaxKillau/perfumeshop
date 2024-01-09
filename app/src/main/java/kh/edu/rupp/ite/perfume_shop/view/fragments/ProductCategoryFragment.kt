package kh.edu.rupp.ite.perfume_shop.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kh.edu.rupp.ite.perfume_shop.adapter.ProductAdapter
import kh.edu.rupp.ite.perfume_shop.adapter.ProductCategoryAdapter
import kh.edu.rupp.ite.perfume_shop.api.model.ProductCategory
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.databinding.FragementCategoryProductsBinding
import kh.edu.rupp.ite.perfume_shop.view.activity.MainActivity
import kh.edu.rupp.ite.perfume_shop.viewmodel.ProductCategoryViewModel


class ProductCategoryFragment: Fragment {

    private lateinit var binding: FragementCategoryProductsBinding
    private val productCategoryViewModel = ProductCategoryViewModel();
    private var id: Int = 0;
    private lateinit var mainActivity: MainActivity
    private lateinit var fragment: Fragment;

    constructor() : super()

    // Constructor with id parameter
    constructor(id: Int , fragment: Fragment) : this() {
        this.id = id
        this.fragment = fragment
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragementCategoryProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productCategoryViewModel.loadCategoryProduct(id)
        mainActivity = activity as MainActivity
        productCategoryViewModel.productData.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.PROCESSING-> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> {
                    val data = it.data
                    if (data != null) {

                        // Additional handling of the data if needed
                        showProduct(it.data.data)
//                        Log.d("Dattt" , it.data.data.toString())
                    } else {
                        Log.d("Data12222", "Data is null")
                    }
                }
                Status.ERROR -> Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show()
            }
        }

    }
    fun showProduct(product: List<ProductCategory>){

        val spanCount = 1 // Set the desired number of columns
        val gridLayoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.backBtn.setOnClickListener{
            mainActivity.changeFragment(fragment)
        }


        val  productCategoryAdapter: ProductCategoryAdapter = ProductCategoryAdapter();
        Log.d("List" , product[0].products.toString())
        Log.d("ProductAdapter", "Product list size: ${product[0].products.size}")
        productCategoryAdapter.submitList(product[0].products);

        binding.recyclerView.adapter = productCategoryAdapter;
    }


}