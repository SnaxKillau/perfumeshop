package kh.edu.rupp.ite.perfume_shop.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.perfume_shop.api.model.Product
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.databinding.FragementProductDetailBinding
import kh.edu.rupp.ite.perfume_shop.databinding.FragmentCategoriesBinding
import kh.edu.rupp.ite.perfume_shop.view.activity.MainActivity
import kh.edu.rupp.ite.perfume_shop.viewmodel.ProductDetailViewModel
import kh.edu.rupp.ite.perfume_shop.viewmodel.ShoppingBagViewModel

class ProductDetailFragment: Fragment {

    private lateinit var binding: FragementProductDetailBinding
    private val productDetailViewModel = ProductDetailViewModel();
    private val shoppingBagViewModel = ShoppingBagViewModel();
    private lateinit var mainActivity: MainActivity
    private lateinit var fragment: Fragment;
    private var id: Int = 0;

    // Default (empty) constructor
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
        binding = FragementProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productDetailViewModel.loadProductDetail(id);
        mainActivity = activity as MainActivity

        productDetailViewModel.productData.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.PROCESSING-> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> {
                    val data = it.data
                    if (data != null) {

                        // Additional handling of the data if needed
                        showProduct(it.data.data)
                    } else {
                        Log.d("Data12222", "Data is null")
                    }
                }
                Status.ERROR -> Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun showProduct(product: Product){

        binding.productBrand.text = "Brand: " + product.brand
        binding.productName.text = "Name: " + product.name
        binding.productPrice.text = "Price: " + product.price.toString()
        binding.available.text = "Available Unit: " + product.availableUnit.toString()
        binding.productDescription.text = product.decription
        binding.backBtn.setOnClickListener{
            mainActivity.changeFragment(fragment)
        }
        binding.buyingbtn.setOnClickListener{
            shoppingBagViewModel.PostingProductsFromApi(product.id.toInt())
        }
        val imageUrl: String? = product.image.firstOrNull()?.url
        Log.d("img" , imageUrl.toString())



        // Use Picasso to load the image
        if (imageUrl != null) {
            //Check if imageUrl is not null before concatenating
            val fullImageUrl = "http://10.0.2.2:8888/images/$imageUrl"
            Log.d("img" , fullImageUrl.toString())
            Picasso.get().load(fullImageUrl)
                .resize(200 , 200)
                .into(binding.imgProduct, object : Callback {
                    override fun onSuccess() {
                        // Image loaded successfully
                    }

                    override fun onError(e: Exception?) {
                        // Handle error
                        Log.e("Picasso", "Error loading image", e)
                    }
                })
        }
    }
}