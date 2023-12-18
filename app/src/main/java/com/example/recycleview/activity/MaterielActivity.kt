package com.example.recycleview.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.recycleview.Fragment.HomeFragment
import com.example.recycleview.R
import com.example.recycleview.databinding.ActivityMaterielBinding

import com.example.recycleview.viewModel.MaterielViewModel

private lateinit var binding: com.example.recycleview.databinding.ActivityMaterielBinding
private lateinit var Materielid:String
private lateinit var MaterielName:String
private lateinit var MaterielThum:String
private lateinit var MaterielType:String
private lateinit var MaterielStatus:String

private lateinit var Materieldescription:String
private lateinit var materielMvvm:MaterielViewModel
class MaterielActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaterielBinding.inflate(layoutInflater)
        setContentView(binding.root)
        materielMvvm = ViewModelProviders.of(this)[MaterielViewModel::class.java]
        getMaterielInformationFromIntent()
        setInformationInviews()
        materielMvvm.getMaterielDetails(Materielid)
        observerMaterielDetailslivedata()
        binding.logo.setOnClickListener {
            this.run{
                startActivity(Intent(this,  BaseActivity::class.java))
                finish()        }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun observerMaterielDetailslivedata() {
materielMvvm.observerMaterielDetailsLiveData().observe(this
) { t ->
    if (t != null) {
    }
}
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setInformationInviews() {
Glide.with(applicationContext)
    .load(MaterielThum)
    .into(binding.imgMaterielDetails)
    binding.CollapsingTollbar.title = MaterielName
        binding.CollapsingTollbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
binding.CollapsingTollbar.setExpandedTitleColor(resources.getColor(R.color.white))
        binding.category.text= MaterielType
        binding.Instruction.text= Materieldescription
        binding.status.text= MaterielStatus


    }

    private fun getMaterielInformationFromIntent() {
val intent=intent
    Materielid=intent.getStringExtra(HomeFragment.Materiel_id)!!
        MaterielName=intent.getStringExtra(HomeFragment.Materiel_Name)!!
        MaterielThum=intent.getStringExtra(HomeFragment.Materiel_thum)!!
        MaterielType=intent.getStringExtra(HomeFragment.Materiel_type)!!
        Materieldescription=intent.getStringExtra(HomeFragment.Materiel_description)!!
        MaterielStatus=intent.getStringExtra(HomeFragment.Materiel_Status)!!



    }
    private fun loadingCase(){
        binding.progressBar.visibility=View.VISIBLE
        binding.InstructionF.visibility=View.INVISIBLE
        binding.category.visibility=View.INVISIBLE

    }
    private fun onresponseCase(){
        binding.progressBar.visibility=View.INVISIBLE
        binding.InstructionF.visibility=View.VISIBLE
        binding.category.visibility=View.VISIBLE
    }
}