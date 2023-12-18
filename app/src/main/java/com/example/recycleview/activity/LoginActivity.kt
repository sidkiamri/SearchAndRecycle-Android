package com.example.recycleview.activity


import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleview.databinding.ActivityLoginBinding
import com.example.recycleview.pojo.BaseResponse
import com.example.recycleview.pojo.user
import com.example.recycleview.retrofit.sessionManager
import com.example.recycleview.viewModel.LoginViewModel



class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    lateinit var binding1: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val token = sessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {

                    stopLoading()
                    processLogin(it.data)



                    val intent = Intent(this@LoginActivity, BaseActivity::class.java)

                    startActivity(intent)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                    sessionManager.clearData(this.applicationContext)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.singIn.setOnClickListener {
            doLogin()
                val sp1 = getSharedPreferences("Login", MODE_PRIVATE)

                val unm = sp1.getString("Unm","def")
                if(unm == "def"){
                    var intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    sessionManager.clearData(this.applicationContext)

                }
                else {
                    var intent = Intent(applicationContext, BaseActivity::class.java)
                    startActivity(intent)
                }

        }



    }

    private fun navigateToHome() {

        val intent = Intent(this, BaseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    fun doLogin() {
        val email = binding.eMail.text.toString()
        val pwd = binding.passwords.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)

    }

    fun doSignup() {

    }

    fun showLoading() {
        binding.spinnerLoader.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.spinnerLoader.visibility = View.GONE
    }

    fun processLogin(data: user?) {
        showToast("Success:" + data?.message)
        if (!data?.token.isNullOrEmpty()) {


            data?.token?.let { sessionManager.saveAuthToken(this, it) }
            navigateToHome()
        }
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}







