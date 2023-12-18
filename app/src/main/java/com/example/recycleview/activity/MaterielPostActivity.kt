package com.example.recycleview.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.recycleview.R
import com.example.recycleview.app.MyApp
import com.example.recycleview.databinding.AcitivityRegisterBinding
import com.example.recycleview.databinding.ActivityMaterielPostBinding
import com.example.recycleview.pojo.BaseResponse
import com.example.recycleview.pojo.updateRequest
import com.example.recycleview.pojo.updateResponse
import com.example.recycleview.viewModel.EventModelView
import com.example.recycleview.viewModel.ProfileViewModel
import com.example.recycleview.viewModel.RegisterViewModel
import com.example.recycleview.viewModel.UpdateViewModel
import kotlin.properties.Delegates

class MaterielPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMaterielPostBinding
    private lateinit var homeMvvm: UpdateViewModel
    private val viewModel by viewModels<UpdateViewModel>()
    private lateinit var Profile: ProfileViewModel
    var points =5

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaterielPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        homeMvvm= ViewModelProviders.of(this)[UpdateViewModel::class.java]
        Profile = ViewModelProviders.of(this)[ProfileViewModel::class.java]
        Profile.loadProfile()

        viewModel.updateResult.observe(this) {
            when (it) {


                is BaseResponse.Loading -> {
                    showLoading()
                }


                is BaseResponse.Success -> {
                    stopLoading()



                    val intent = Intent(this@MaterielPostActivity, BaseActivity::class.java)

                    startActivity(intent)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.ClaimButton.setOnClickListener {
val sp = getSharedPreferences("Login",Context.MODE_PRIVATE)
            val tpt = sp.getInt("pts",0)
                homeMvvm.updatepoints(points = points+tpt)


        }




    }
    fun som(): Int {
       var i=0

        do {


                points += 5
            ++i
        }
        while (  i<50)
        return points
    }

    private fun observerRandomuser() {

        Profile.observerDetailsLiveData().observe(
        ) { Materiel ->
            if (Materiel != null) {

            }




        }
    }


    private fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE

    }
    private fun processError(msg: String?) {
        showToast("Error:$msg")
    }
    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun stopLoading() {
        binding.prgbar.visibility = View.GONE

    }
}

private fun <T> LiveData<T>.observe(function: (T?) -> Unit) {

}
