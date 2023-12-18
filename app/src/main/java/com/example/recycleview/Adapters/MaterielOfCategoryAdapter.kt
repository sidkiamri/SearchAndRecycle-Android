package com.example.recycleview.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleview.databinding.MaterielcatBinding
import com.example.recycleview.pojo.MaterielX
import com.example.recycleview.pojo.MaterielXX

class MaterielOfCategoryAdapter :RecyclerView.Adapter<MaterielOfCategoryAdapter.CategoryViewModel>(){
    private var materielssList =ArrayList<MaterielXX>()
    fun setMaterielList(materielssList: List<MaterielXX>){
        this.materielssList=materielssList as ArrayList<MaterielXX>
        notifyDataSetChanged()
    }
    inner class CategoryViewModel(val binding: MaterielcatBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewModel {
return CategoryViewModel(
    MaterielcatBinding.inflate(
        LayoutInflater.from(parent.context)
    )
)
    }

    override fun onBindViewHolder(holder: CategoryViewModel, position: Int) {
Glide.with(holder.itemView).load(materielssList[position].matrielImage).into(holder.binding.imgMateriel)
    holder.binding.tvMaterielName.text=materielssList[position].materielName
    }

    override fun getItemCount(): Int {
return materielssList.size   }
}