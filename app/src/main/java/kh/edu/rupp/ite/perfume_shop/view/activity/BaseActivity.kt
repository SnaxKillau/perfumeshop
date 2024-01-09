package kh.edu.rupp.ite.perfume_shop.view.activity

import android.app.Dialog
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.perfume_shop.R
import kh.edu.rupp.ite.perfume_shop.databinding.ActivityLoadingBinding

open class BaseActivity:AppCompatActivity() {

    private lateinit var mProgressDialog: Dialog
    private lateinit var binding: ActivityLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_empty)
    }
    fun showProgressDialog(text: String) {
        mProgressDialog = Dialog(this)

        // Inflate the dialog_progress layout
        mProgressDialog.setContentView(R.layout.activity_loading)

        // Find the TextView within the inflated layout
        val progressText = mProgressDialog.findViewById<TextView>(R.id.tv_progress_text)

        // Set the text for the TextView
        progressText.text = text

        // Show the progress dialog
        mProgressDialog.show()
    }
    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }
}