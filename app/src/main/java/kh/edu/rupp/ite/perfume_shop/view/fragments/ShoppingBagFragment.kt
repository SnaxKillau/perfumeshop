package kh.edu.rupp.ite.perfume_shop.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kh.edu.rupp.ite.perfume_shop.adapter.ShoppingBagAdapter
import kh.edu.rupp.ite.perfume_shop.api.model.Product
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.databinding.FragementShoppingBagBinding
import kh.edu.rupp.ite.perfume_shop.viewmodel.ShoppingBagViewModel

class ShoppingBagFragment: Fragment() {
    private lateinit var binding: FragementShoppingBagBinding
    private val shoppingBagViewModel = ShoppingBagViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragementShoppingBagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoppingBagViewModel.GetProductFromApi();
        shoppingBagViewModel.productData.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.PROCESSING-> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> {
                    val data = it.data
                    if (data != null) {
                        Log.d("Data12222", data.data.toString());
                        // Additional handling of the data if needed
                        showProductsList(it.data.data)
                    } else {
                        Log.d("Data12222", "Data is null")
                    }
                }
                Status.ERROR -> Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun showProductsList(productList: List<Product>?){
        val gridLayoutManager: GridLayoutManager = GridLayoutManager(context,1)
        binding.recyclerView.layoutManager = gridLayoutManager;
        val shoppingBagAdapter:ShoppingBagAdapter = ShoppingBagAdapter()
        shoppingBagAdapter.submitList(productList)
        binding.recyclerView.adapter = shoppingBagAdapter
        binding.buyingbtn.setOnClickListener{
            shoppingBagViewModel.DeleteProductFromApi()
        }
    }
    }

