package com.example.recycleview.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleview.databinding.PopularItemBinding
import com.example.recycleview.pojo.Materiel
import com.example.recycleview.pojo.MaterielX
import com.example.recycleview.pojo.materielList

class MaterielAdapter :RecyclerView.Adapter<MaterielAdapter.PopularMaterielViewHolder>(){
    class PopularMaterielViewHolder(  val binding:PopularItemBinding):RecyclerView.ViewHolder(binding.root)
    lateinit var onItemClick:((MaterielX)->Unit)
private var materielList =ArrayList<MaterielX>()
        fun setMateriels(materielList:ArrayList<MaterielX>){
        this.materielList=materielList
        notifyDataSetChanged()
    }
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMaterielViewHolder {

return PopularMaterielViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMaterielViewHolder, position: Int) {
Glide.with(holder.itemView)
    .load(materielList[position].matrielImage)
    .into(holder.binding.imgPopularMaterielItem)
    holder.itemView.setOnClickListener {
        onItemClick.invoke(materielList[position])
    }

    }
    override fun getItemCount(): Int {
return materielList.size   }

}