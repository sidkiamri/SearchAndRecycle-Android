package com.example.recycleview.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.example.recycleview.R
import com.example.recycleview.databinding.ActivityPhotoBinding
import com.example.recycleview.utils.Draw
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions
import java.util.concurrent.ExecutorService


class xcameraActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPhotoBinding
    private val REQUEST_CODE_PERMISSIONS = 101
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    private lateinit var binding: ActivityPhotoBinding
    private lateinit var objectDetector:ObjectDetector
    private lateinit var cameraProviderFuture:ListenableFuture<ProcessCameraProvider>
    private val permissions = arrayOf(
        "android.permission.CAMERA",
        "android.hardware.camera.any"
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val requestCode = 200
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo)
        // Request camera permissions

        val localModel = LocalModel.Builder().setAssetFilePath("object.tflite").build()

        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider) },ContextCompat.getMainExecutor(this))


        val customObjectDetectorOptions =
            CustomObjectDetectorOptions.Builder(localModel)
                .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
                .enableClassification()
                .setClassificationConfidenceThreshold(0.5f)
                .setMaxPerObjectLabelCount(3)
                .build()

        objectDetector = ObjectDetection.getClient(customObjectDetectorOptions)

        /*  if (allPermissionsGranted()) {
            }
            },ContextCompat.getMainExecutor(this))
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)}
*/

    }
    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("UnsafeOptInUsageError")
    private fun bindPreview(cameraProvider: ProcessCameraProvider){
        val preview =Preview.Builder().build()
        val cameraSelector=CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)
        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(800,800)
            )
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this)) { imageProxy ->
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            val image = imageProxy.image

            if (image != null) {
                val processImage = InputImage.fromMediaImage(image, rotationDegrees)
                objectDetector
                    .process(processImage)
                    .addOnSuccessListener {
                            objects -> for (i in objects) {
                        if(binding.parentLayout.childCount>1)

                            binding.parentLayout.removeViewAt(2)

                        var tt = "";
                        if (i.labels.firstOrNull()?.text == "Water bottle" || i.labels.firstOrNull()?.text == "Bottle" || i.labels.firstOrNull()?.text == "Container"){
                            tt = i.labels.firstOrNull()!!.text
                            val intent = Intent(this@xcameraActivity, MaterielPostActivity::class.java)
                            startActivity(intent)
                        }else {   tt = "scan another"}
                        val element = Draw(context = this,
                            rect = i.boundingBox,
                            text = tt

                        )


                        i.labels.size.rem(40f)

                        binding.parentLayout.addView(element)

                    }
                        imageProxy.close()
                    }.addOnFailureListener {
                        Log.v("photoActivity", "Error _ ${it.message}")
                        imageProxy.close()
                    }

            }
        }
        binding.imageCaptureButton.setOnClickListener { takePhoto()}
        val imageCapture = ImageCapture.Builder()
            .setTargetRotation(binding.parentLayout.display.rotation)
            .build()
        cameraProvider.bindToLifecycle(
            this as LifecycleOwner,
            cameraSelector,
            imageAnalysis,
            preview
        )




    }

    private var our_request_code: Int = 123 //can number can be given

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun takePhoto() {


    }}


