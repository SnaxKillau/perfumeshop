package kh.edu.rupp.ite.perfume_shop.view.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.perfume_shop.R
import kh.edu.rupp.ite.perfume_shop.databinding.ActivityMainBinding
import kh.edu.rupp.ite.perfume_shop.view.fragments.CategoriesFragment
import kh.edu.rupp.ite.perfume_shop.view.fragments.HomeFragment
import kh.edu.rupp.ite.perfume_shop.view.fragments.ProductDetailFragment
import kh.edu.rupp.ite.perfume_shop.view.fragments.ShoppingBagFragment
import kh.edu.rupp.ite.perfume_shop.view.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
        viewFragment(HomeFragment())


        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> viewFragment(HomeFragment())
                R.id.categories -> viewFragment(CategoriesFragment())
                R.id.bag -> viewFragment(ShoppingBagFragment())
                R.id.profile -> viewFragment(ProfileFragment())
                else -> viewFragment(CategoriesFragment())

            }
            true
        }

//
//        val imageSlider = findViewById<ImageSlider>(R.id.imgSlider)
//        val imageList = ArrayList<SlideModel>()
//
//        imageList.add(SlideModel("https://cdn.saksfifthavenue.com/is/image/saks/0400095879372_NOCOLOR_A3?wid=484&hei=646&qlt=90&resMode=sharp2&op_usm=0.9,1.0,8,0", "Baccarat Rouge"))
//        imageList.add(SlideModel("https://dimg.dillards.com/is/image/DillardsZoom/mainProduct/yves-saint-laurent-y-eau-de-parfum-for-men/00000001_zi_05465149.jpg", "YSL"))
//        imageList.add(SlideModel("https://parfums-de-marly.com/cdn/shop/files/2208_LAYTON_EXCLUSIF_125ml_grande.jpg?v=1683792659", "PARFUMS DE MARLY"))
//        imageList.add(SlideModel("https://creedboutique.com/cdn/shop/files/aventus_moonrock.jpg?v=1692029051&width=750", "Creed Aventus"))
//        imageList.add(SlideModel("https://static.thcdn.com/images/small/original/widgets/95-en/21/original-TF_LookFantastic_ShopMore_OmbreLeather_507x417-024921.jpg", "Tom Ford"))
//        imageList.add(SlideModel("https://images.summitmedia-digital.com/esquiremagph/images/2019/12/27/Most-Iconic-Men's-Fragrances-MAIN-IMAGE.jpg", ""))
//
//        imageSlider.setImageList(imageList, ScaleTypes.FIT)


    }
    fun changeFragment(fragment: Fragment) {
        viewFragment(fragment)
    }

    private fun viewFragment(fragment:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lytFragment,fragment);
        fragmentTransaction.commit();
    }

}