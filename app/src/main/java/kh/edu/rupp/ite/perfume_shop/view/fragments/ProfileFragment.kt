package kh.edu.rupp.ite.perfume_shop.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import androidx.lifecycle.lifecycleScope
import kh.edu.rupp.ite.perfume_shop.R
import kh.edu.rupp.ite.perfume_shop.core.AppCore
import kh.edu.rupp.ite.perfume_shop.databinding.FragementProfileBinding
import kh.edu.rupp.ite.perfume_shop.utility.AppEncryptedPreference
import kh.edu.rupp.ite.perfume_shop.view.activity.MainActivity
import kh.edu.rupp.ite.perfume_shop.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {


    private lateinit var binding: FragementProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var mainActivity: MainActivity



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragementProfileBinding.inflate(inflater, container, false)
        return binding.root
        profileViewModel = ProfileViewModel();
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity
        showProfile()
//
//        profileViewModel.profileData.observe(viewLifecycleOwner) {
//            when(it.status) {
//                Status.PROCESSING -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
//                Status.SUCCESS ->{
//                    val token = it.token
//
//                    if (token != null) {
//
//                        // Additional handling of the data if needed
//                        showProfile()
//                    }else {
//                        Log.d("Data12222", "Data is null")
//                    }
//
//                }
//                Status.ERROR -> Toast.makeText(requireContext(),"Failed", Toast.LENGTH_SHORT).show()
//
//
//        }
//    }
    }



    fun showProfile(){
        val dataToSend = "Hello from FragmentA"
        val bundle = Bundle()
        bundle.putString("username", binding.txtUsername.text.toString())
        bundle.putString("email" , binding.txtEmail.text.toString())
        val editFragment = EditFragment()
        editFragment.arguments = bundle
        binding.editbutt.setOnClickListener{
            mainActivity.changeFragment(editFragment)
        }

        val email = binding.txtEmail.text.toString();
        val username = binding.txtUsername.toString();

    }




}






