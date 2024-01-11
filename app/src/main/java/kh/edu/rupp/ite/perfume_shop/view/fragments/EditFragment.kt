package kh.edu.rupp.ite.perfume_shop.view.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kh.edu.rupp.ite.perfume_shop.databinding.FragementEditBinding
import kh.edu.rupp.ite.perfume_shop.view.activity.MainActivity
import kh.edu.rupp.ite.perfume_shop.viewmodel.EditViewModel
import kotlinx.coroutines.launch


class EditFragment : Fragment() {

     private lateinit var binding: FragementEditBinding
     private lateinit var mainActivity: MainActivity
     private var editViewModel = EditViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragementEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val username = arguments?.getString("username")
        val email = arguments?.getString("email")
        // Initialize mainActivity here
        mainActivity = activity as MainActivity
        Log.d("me" , username.toString())
        Log.d("me" , email.toString())
        val usernameEditable: Editable? = Editable.Factory.getInstance().newEditable(username)
        val emailEditable: Editable? = Editable.Factory.getInstance().newEditable(email)

           // Set the Editable values to the EditText views
        binding.txtFName.text = usernameEditable
        binding.txtEmail.text = emailEditable
        var usernameText = binding.txtFName.text
        var emailText = binding.txtEmail.text
        binding.cancleBtn.setOnClickListener {
            mainActivity.changeFragment(ProfileFragment())
        }
        binding.saveBtn.setOnClickListener {
            lifecycleScope.launch {
               val editSuccessful =  editViewModel.edit(emailText.toString(),usernameText.toString())

                if(editSuccessful){
                    mainActivity.changeFragment(ProfileFragment())
                }
            }

        }
        super.onViewCreated(view, savedInstanceState)
    }


}

