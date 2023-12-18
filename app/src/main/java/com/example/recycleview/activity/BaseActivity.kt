package com.example.recycleview.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.recycleview.Fragment.HomeFragment
import com.example.recycleview.Fragment.ProfileFragment
import com.example.recycleview.Fragment.categoriesFragment
import com.example.recycleview.Fragment.mapFragment
import com.example.recycleview.R
import com.example.recycleview.databinding.ActivityBaseBinding
import com.example.recycleview.retrofit.sessionManager

class BaseActivity: AppCompatActivity() {
    private lateinit var binding :ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCurrentFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> replaceFragment(HomeFragment())
                R.id.miLearn -> replaceFragment(categoriesFragment())
                R.id.miLocation -> replaceFragment(mapFragment())
                R.id.miProfile -> replaceFragment(ProfileFragment())

                else -> {

                }
            }
            true


        }
        binding.fab.setOnClickListener {
            val myIntent = Intent(this, xcameraActivity::class.java)

            startActivity(myIntent)     }


    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FrameLayout,fragment)
            commit()
        }
    override fun onBackPressed() {
        moveTaskToBack(true)
        sessionManager.clearData(this.applicationContext)
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FrameLayout,fragment)
        fragmentTransaction.commit()
    }
}