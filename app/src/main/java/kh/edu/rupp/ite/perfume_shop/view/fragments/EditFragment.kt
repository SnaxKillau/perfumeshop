package kh.edu.rupp.ite.perfume_shop.view.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.perfume_shop.databinding.FragementEditBinding

class EditFragment : Fragment() {

     private lateinit var binding: FragementEditBinding

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
        Log.d("me" , username.toString())
        Log.d("me" , email.toString())
        val usernameEditable: Editable? = Editable.Factory.getInstance().newEditable(username)
        val emailEditable: Editable? = Editable.Factory.getInstance().newEditable(email)

           // Set the Editable values to the EditText views
        binding.txtFName.text = usernameEditable
        binding.txtEmail.text = emailEditable
        super.onViewCreated(view, savedInstanceState)
    }


}

