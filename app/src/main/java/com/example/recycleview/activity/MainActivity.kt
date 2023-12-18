package com.example.recycleview.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.recycleview.Fragment.ProfileFragment
import com.example.recycleview.Fragment.categoriesFragment
import com.example.recycleview.Fragment.mapFragment
import com.example.recycleview.Fragment.HomeFragment
import com.example.recycleview.R
import com.example.recycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }

}