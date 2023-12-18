package com.example.recycleview.Fragment

import android.content.Intent
import android.os.Build.VERSION_CODES.M
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recycleview.Adapters.CategoryAdapter
import com.example.recycleview.Adapters.MaterielAdapter
import com.example.recycleview.Fragment.HomeFragment.Companion.Materiel_Name
import com.example.recycleview.Fragment.HomeFragment.Companion.Materiel_description
import com.example.recycleview.Fragment.HomeFragment.Companion.Materiel_id
import com.example.recycleview.Fragment.HomeFragment.Companion.Materiel_thum
import com.example.recycleview.Fragment.HomeFragment.Companion.Materiel_type
import com.example.recycleview.activity.CategoryMaterielActivity
import com.example.recycleview.activity.MaterielActivity
import com.example.recycleview.databinding.FragmentHomeBinding
import com.example.recycleview.pojo.Mat
import com.example.recycleview.pojo.Materiel
import com.example.recycleview.pojo.MaterielX
import com.example.recycleview.pojo.materielList
import com.example.recycleview.viewModel.HomeViewModel


class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding
private lateinit var homeMvvm:HomeViewModel
private lateinit var randomMateriel: Materiel
private lateinit var popularItemsAdapter:MaterielAdapter
private lateinit var categoriesAdapter: CategoryAdapter

    companion object{
    const val Materiel_id="package com.example.recycleview.Fragment.idMateriel"
    const val Materiel_Name="package com.example.recycleview.Fragment.Materiel_Name"
    const val Materiel_thum="package com.example.recycleview.Fragment.Materiel_thumb"
    const val Materiel_type="package com.example.recycleview.Fragment.Materiel_type"
    const val Materiel_description="package com.example.recycleview.Fragment.Materiel_description"
        const val Materiel_Status="package com.example.recycleview.Fragment.Materiel_Status"

        const val CATEGORY_ID="com.example.recycleview.Fragment.CATEGORY_ID"


}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        homeMvvm=ViewModelProviders.of(this)[HomeViewModel::class.java]
        popularItemsAdapter= MaterielAdapter()
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePopularItemRecyclerView()
homeMvvm.getRandomMateriel()
        observerRandomMateriel()
onRandomMaterielClick()
        homeMvvm.getPopularMateriel()
        observerPopularItemLiveData()
        OnpopularItemClick()

        prepareCategoriesRecycleView()
        homeMvvm.getCategories()
        observeCategoriesliveData()
        oncategoryClick()
    }

    private fun oncategoryClick() {
categoriesAdapter.onItemclick={category->
    val intent=Intent(activity,CategoryMaterielActivity::class.java)
    intent.putExtra(CATEGORY_ID,category.id)
    startActivity(intent)
}    }

    private fun prepareCategoriesRecycleView() {
        categoriesAdapter= CategoryAdapter()
binding.recView.apply {
    layoutManager=GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
    adapter=categoriesAdapter
}    }

    private fun observeCategoriesliveData() {
homeMvvm.observablecategoriesLiveData().observe(viewLifecycleOwner, Observer{ categories ->
    categoriesAdapter.setCategoryList(categories)



})    }

    private fun OnpopularItemClick() {
popularItemsAdapter.onItemClick ={
    materiel ->
    val intent = Intent(activity,MaterielActivity::class.java)
    intent.putExtra(Materiel_Name,materiel.materielName)
    intent.putExtra(Materiel_type,materiel.type.typename)
    intent.putExtra(Materiel_id,materiel.id)
    intent.putExtra(Materiel_description,materiel.description)
    intent.putExtra(Materiel_thum,materiel.matrielImage)
    intent.putExtra(Materiel_Status,materiel.status)

    startActivity(intent)
}   }

    private fun preparePopularItemRecyclerView() {
binding.recycleview.apply {
    layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    adapter=popularItemsAdapter

}  }

    private fun observerPopularItemLiveData() {
        homeMvvm.observerPopularItemsLiveData().observe(viewLifecycleOwner
        ) { Mat->
           popularItemsAdapter.setMateriels(materielList = Mat as ArrayList<MaterielX>)
        }
    }

    private fun onRandomMaterielClick(){
        binding.randomRecycleCard.setOnClickListener{
            val intent=Intent(activity,MaterielActivity::class.java)
            intent.putExtra(Materiel_id,randomMateriel.id)
            intent.putExtra(Materiel_Name,randomMateriel.materielName)
            intent.putExtra(Materiel_thum,randomMateriel.matrielImage)
            intent.putExtra(Materiel_type,randomMateriel.type.type_name)
            intent.putExtra(Materiel_description,randomMateriel.description)
            intent.putExtra(Materiel_Status,randomMateriel.status)

            startActivity(intent)
        }





    }
    private fun observerRandomMateriel(){
        homeMvvm.ObserveRandomLiveData().observe(viewLifecycleOwner
        ) { Materiel ->
            Glide.with(this@HomeFragment)
                .load(Materiel!!.matrielImage)
                .into(binding.imgRandomRecycle)
            this.randomMateriel = Materiel


        }
    }}



