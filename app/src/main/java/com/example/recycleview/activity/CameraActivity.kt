package com.example.recycleview.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.recycleview.R
import com.example.recycleview.databinding.ActivityCameraBinding
private var our_request_code: Int = 123 //can number can be given

class CameraActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCameraBinding
    private val REQUEST_CODE_PERMISSIONS = 101
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_camera)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
        val image_view = binding.image as ImageView

        val btn_click_me1 = binding.Button as Button
        btn_click_me1.setOnClickListener {
            TakePhoto(image_view)
        }
    }

    fun TakePhoto(view: View) {


        //start an intent to capture image
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        //start the result

        //check if the task can be performed
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, our_request_code)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == our_request_code && resultCode == RESULT_OK) {
            //if the result is ok our request code is equal to request code
            val imageview: ImageView = findViewById(R.id.image)
            //start bitmap
            val bitmap = data?.extras?.get("data") as Bitmap

            //set image bitmap
            imageview.setImageBitmap(bitmap)
        }
    }
}