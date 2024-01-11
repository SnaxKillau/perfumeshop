package kh.edu.rupp.ite.perfume_shop.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kh.edu.rupp.ite.perfume_shop.api.model.Product
import kh.edu.rupp.ite.perfume_shop.databinding.FragmentHomeBinding
import kh.edu.rupp.ite.perfume_shop.adapter.ProductAdapter
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.view.activity.MainActivity
import kh.edu.rupp.ite.perfume_shop.viewmodel.ProductDetailViewModel
import kh.edu.rupp.ite.perfume_shop.viewmodel.ProductsViewModel

class HomeFragment: Fragment() {
    private lateinit var binding:FragmentHomeBinding;
    private lateinit var mainActivity: MainActivity
    private val viewModel = ProductsViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize mainActivity here
        mainActivity = activity as MainActivity

        viewModel.loadProductsFromApi();
        viewModel.productData.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.PROCESSING-> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> showProductsList(it.data?.data)
                Status.ERROR -> Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun showProductsList(productList: List<Product>?){



        val gridLayoutManager:GridLayoutManager = GridLayoutManager(context, productList?.size!!)
        binding.recyclerView.layoutManager = gridLayoutManager;

        val  productAdapter: ProductAdapter = ProductAdapter();
        productAdapter.onProductsClickListener = {index:Int , product:Product ->
            Log.d("fragmentNumb" , index.toString());

            mainActivity.changeFragment(ProductDetailFragment(product.id.toInt() , HomeFragment()));

        }
        productAdapter.submitList(productList);



        binding.recyclerView.adapter = productAdapter;


    }




}