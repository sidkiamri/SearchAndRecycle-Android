package com.example.recycleview.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.recycleview.Fragment.HomeFragment
import com.example.recycleview.Fragment.categoriesFragment
import com.example.recycleview.R
import com.example.recycleview.databinding.ActivityEventsBinding
import com.example.recycleview.databinding.ActivityMaterielBinding
import com.example.recycleview.viewModel.MaterielViewModel

private lateinit var binding:ActivityEventsBinding
private lateinit var eventid:String
private lateinit var eventsName:String
private lateinit var eventsThum:String
private lateinit var eventsnipper:String
private lateinit var eventdescription:String
class eventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logo.setOnClickListener {
            this.run {
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            }
        }




            getMaterielInformationFromIntent()
            setInformationInviews()


    }

    private fun setInformationInviews() {
        Glide.with(applicationContext)
            .load(eventsThum)
            .into(binding.imgEventsDetails)
        binding.CollapsingTollbar.title = eventsName
        binding.CollapsingTollbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.CollapsingTollbar.setExpandedTitleColor(resources.getColor(R.color.white))
        binding.snippet.text= eventsnipper
        binding.Instruction.text= eventdescription

    }

    private fun getMaterielInformationFromIntent() {
        val intent = intent
        eventid=intent.getStringExtra(categoriesFragment.event_id)!!
        eventdescription=intent.getStringExtra(categoriesFragment.event_description)!!
        eventsName=intent.getStringExtra(categoriesFragment.title)!!
        eventsnipper=intent.getStringExtra(categoriesFragment.event_snippet)!!
        eventsThum=intent.getStringExtra(categoriesFragment.event_thum)!!

    }
}