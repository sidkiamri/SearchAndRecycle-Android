package com.example.recycleview.activity

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleview.app.MyApp
import com.example.recycleview.databinding.AcitivityRegisterBinding
import com.example.recycleview.pojo.*
import com.example.recycleview.retrofit.MyApi
import com.example.recycleview.retrofit.sessionManager
import com.example.recycleview.viewModel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class RegisterActivity : AppCompatActivity(), UploadRequestBody.UploadCallBack {
    private var selectedImageUri: Uri? = null
    private lateinit var binding: AcitivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = AcitivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.personavatar.setOnClickListener {
            openImageChooser()
        }
        binding.uploadbtn.setOnClickListener {
            uploadImage()

        }
        val token = sessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.registerResult.observe(this) {
            when (it) {


                is BaseResponse.Loading -> {
                    showLoading()
                }


                is BaseResponse.Success -> {
                    stopLoading()

                    processRegister(it.data)


                    val intent = Intent(this@RegisterActivity, BaseActivity::class.java)

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

        binding.singIn.setOnClickListener {
            doRegister()


        }


    }

    private fun uploadImage() {
        if (selectedImageUri == null) {
            //   layout_root.snackbar("Select an Image First")
            Log.e("RegisterActivity", "select image first")
            return
        }
        val parcelFileDescriptor = contentResolver.openFileDescriptor(
            selectedImageUri!!, "r", null
        ) ?: return
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        binding.progBar.progress = 0
        val body = UploadRequestBody(file, "image", this)
        MyApi().uploadImage(
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                body
            ),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json")
        ).enqueue(object : Callback<UploadResponse> {
            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                response.body()?.let {

                    // layout_root.snackbar(it.message)

                    binding.progBar.progress = 100
                }
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                // layout_root.snackbar(t.message!!)
                binding.progBar.progress = 0

            }

        })


    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_IMAGE)
        }
    }

    companion object {
        const val REQUEST_CODE_IMAGE = 101
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_IMAGE -> {
                    selectedImageUri = data?.data
                    binding.personavatar.setImageURI(selectedImageUri)
                }
            }
        }
    }


    private fun navigateToHome() {
        val intent = Intent(this, BaseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    private fun doRegister() {
        val FirstName = binding.Firstname.text.toString()
        val LastName = binding.Lastname.text.toString()
        val email = binding.registerEmail.text.toString()
        val Phone = binding.Phone.text.toString()
        val country = binding.country.text.toString()
        val address = binding.Address.text.toString()
        val city = binding.city.text.toString()
        val codePostal = binding.codepostal.text.toString()
        val password = binding.passwords.text.toString()
        viewModel.registerUser(
            FirstName = FirstName,
            LastName = LastName,
            email = email,
            Phone = Phone,
            country = country,
            address = address,
            city = city,
            codePostal = codePostal,
            password = password
        )

    }


    private fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE

    }

    private fun stopLoading() {
        binding.prgbar.visibility = View.GONE

    }

    private fun processRegister(data: user?) {
        showToast("Success:" + data?.message)
        if (!data?.token.isNullOrEmpty()) {


            data?.token?.apply { sessionManager.getToken(MyApp.context).toString() }
            navigateToHome()
        }

    }

    private fun processError(msg: String?) {
        showToast("Error:$msg")
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onProgressUpdate(percentage: Int) {
        binding.progBar.progress = percentage
    }


}

private fun ContentResolver.getFileName(selectedImageUri: Uri): String {
    var name = ""
    val returnCursor = this.query(selectedImageUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}

private fun View.Snackbar(message: String) {

    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }.show()

}
