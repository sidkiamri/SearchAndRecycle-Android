package com.example.recycleview.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recycleview.Adapters.MaterielOfCategoryAdapter
import com.example.recycleview.Fragment.HomeFragment
import com.example.recycleview.R
import com.example.recycleview.databinding.ActivityCategoryMaterielBinding
import com.example.recycleview.viewModel.CategoryModelView

class CategoryMaterielActivity : AppCompatActivity() {
lateinit var categoryModelView: CategoryModelView
    lateinit var  binding:ActivityCategoryMaterielBinding
    lateinit var categoryAdapter: MaterielOfCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryMaterielBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerview()
        categoryModelView= ViewModelProviders.of(this)[CategoryModelView::class.java]
        categoryModelView.getMaterielByCategory(intent.getStringExtra(HomeFragment.CATEGORY_ID)!!)
        categoryModelView.observableMaterielLiveData().observe(this, Observer {MaterielList->

          binding.tvCategoryCount.text=MaterielList.size.toString()
           categoryAdapter.setMaterielList(MaterielList)
            }


       )
    }

    private fun prepareRecyclerview() {
        categoryAdapter=  MaterielOfCategoryAdapter()
    binding.rvMateriel.apply {
        layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        adapter=categoryAdapter
    }



    }
}