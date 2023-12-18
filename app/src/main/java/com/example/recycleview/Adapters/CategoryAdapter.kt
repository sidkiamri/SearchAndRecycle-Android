package com.example.recycleview.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleview.databinding.CategoryItemBinding
import com.example.recycleview.pojo.TypeXX

class CategoryAdapter():RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){


    private var categoryList = ArrayList<TypeXX>()
    var onItemclick:((TypeXX) ->Unit)?=null
    fun setCategoryList(categoryList:List<TypeXX>){
        this.categoryList=categoryList as ArrayList<TypeXX>
        notifyDataSetChanged()
    }
inner class CategoryViewHolder(val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
return CategoryViewHolder(
    CategoryItemBinding.inflate(LayoutInflater.from(parent.context))
  )}

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
Glide.with(holder.itemView).load(categoryList[position].typeImage).into(holder.binding.imgCategory)
    holder.binding.tvCategoryName.text=categoryList[position].typename
    holder.itemView.setOnClickListener {
     onItemclick!!.invoke(categoryList[position])


    }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}