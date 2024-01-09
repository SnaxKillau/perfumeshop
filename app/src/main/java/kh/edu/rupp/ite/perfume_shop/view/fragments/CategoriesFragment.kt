package kh.edu.rupp.ite.perfume_shop.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.model.Categories
import kh.edu.rupp.ite.perfume_shop.databinding.FragmentCategoriesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kh.edu.rupp.ite.perfume_shop.api.service.CategoriesApiService
import kh.edu.rupp.ite.perfume_shop.adapter.CategoriesAdapter
import kh.edu.rupp.ite.perfume_shop.api.model.CategoryResponse
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.view.activity.MainActivity
import kh.edu.rupp.ite.perfume_shop.viewmodel.CategoriesViewModel

class

CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel = CategoriesViewModel()
    private lateinit var mainActivity: MainActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val imageSlider = binding.imgSlider;
        val imageList = ArrayList<SlideModel>()
        // Initialize mainActivity here
        mainActivity = activity as MainActivity

        imageList.add(SlideModel("https://cdn.saksfifthavenue.com/is/image/saks/0400095879372_NOCOLOR_A3?wid=484&hei=646&qlt=90&resMode=sharp2&op_usm=0.9,1.0,8,0", "Baccarat Rouge"))
        imageList.add(SlideModel("https://dimg.dillards.com/is/image/DillardsZoom/mainProduct/yves-saint-laurent-y-eau-de-parfum-for-men/00000001_zi_05465149.jpg", "YSL"))
        imageList.add(SlideModel("https://parfums-de-marly.com/cdn/shop/files/2208_LAYTON_EXCLUSIF_125ml_grande.jpg?v=1683792659", "PARFUMS DE MARLY"))
        imageList.add(SlideModel("https://creedboutique.com/cdn/shop/files/aventus_moonrock.jpg?v=1692029051&width=750", "Creed Aventus"))
        imageList.add(SlideModel("https://static.thcdn.com/images/small/original/widgets/95-en/21/original-TF_LookFantastic_ShopMore_OmbreLeather_507x417-024921.jpg", "Tom Ford"))
        imageList.add(SlideModel("https://images.summitmedia-digital.com/esquiremagph/images/2019/12/27/Most-Iconic-Men's-Fragrances-MAIN-IMAGE.jpg", ""))

        imageSlider.setImageList(imageList, ScaleTypes.FIT)


        viewModel.loadCategories()
        viewModel.categoriesdata.observe(viewLifecycleOwner){
            when(it.status) {
                Status.PROCESSING -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> showCategoriesList(it.data?.data)
                Status.ERROR -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
//        loadCategories()
//
//
    }
//
//    fun loadCategories(){
//
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()
//        val httpClient = Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:8080")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//
//
//        val categoriesApiService: CategoriesApiService = httpClient.create(CategoriesApiService::class.java);
//        val task:Call<List<Categories>> = categoriesApiService.loadCategoriesList();
//
//        task.enqueue(object : Callback<List<Categories>>{
//            override fun onResponse(
//                call: Call<List<Categories>>,
//                response: Response<List<Categories>>
//            ) {
//                if (response.isSuccessful){
//
//                    showCategoriesList(response.body())
//                }else {
//                    Toast.makeText(context, "Load failed!", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<Categories>>, t: Throwable) {
//
//                Log.e(
//                    "[CategoriesFragment]", "Load Fail" +
//                            ": " + t.message
//                )
//                t.printStackTrace();
//            }
//        })
//
//    }

    fun showCategoriesList(categorieslist: List<Categories>?){

        val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = linearLayoutManager;

        val categoriesAdapter: CategoriesAdapter = CategoriesAdapter()

        categoriesAdapter.onCategoriesClickListener = {index:Int , category:Categories ->
           Log.d("fragmentNumb" , index.toString());
            mainActivity.changeFragment(ProductCategoryFragment(category.id.toInt() , CategoriesFragment()))
        }

        categoriesAdapter.submitList(categorieslist)

        binding.recyclerView.adapter = categoriesAdapter

    }

 }




//class CategoriesFragment: Fragment() {
//    private lateinit var binding: FragmentCategoriesBinding;
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
//        return binding.root;
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
////        loadCategories();
//
//    }
//    fun loadCategories(){
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()
//        val httpClient = Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:8080")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//        val productApiService: ProductApiService = httpClient.create(ProductApiService::class.java);
//        val task:Call<List<Categories>> = productApiService.loadCategoriesList();
//
//        task.enqueue(object : Callback<List<Categories>>{
//            override fun onResponse(
//                call: Call<List<Categories>>,
//                response: Response<List<Categories>>
//            ) {
//                if (response.isSuccessful){
//
//                    showCategoriesList(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<List<Categories>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//
//    }
//
//    fun showCategoriesList(categorieslist: List<Categories>?){
//
//        val gridLayoutManager:GridLayoutManager = GridLayoutManager(context, categorieslist?.size!!)
//        binding.recyclerView.layoutManager = gridLayoutManager;
//
//        val  productAdapter:ProductAdapter = ProductAdapter();
//        productAdapter.submitList(categorieslist)
//
//
//        binding.recyclerView.adapter = productAdapter;
//    }
//}