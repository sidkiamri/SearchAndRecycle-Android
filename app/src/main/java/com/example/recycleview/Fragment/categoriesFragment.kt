package com.example.recycleview.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recycleview.Adapters.CategoryAdapter
import com.example.recycleview.Adapters.EventAdapter
import com.example.recycleview.Adapters.MaterielAdapter
import com.example.recycleview.R
import com.example.recycleview.activity.CategoryMaterielActivity
import com.example.recycleview.activity.eventsActivity
import com.example.recycleview.databinding.FragmentCategoriesBinding
import com.example.recycleview.databinding.FragmentHomeBinding
import com.example.recycleview.pojo.Materiel
import com.example.recycleview.viewModel.EventModelView
import com.example.recycleview.viewModel.HomeViewModel


class categoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var homeMvvm: EventModelView
    private lateinit var popularItemsAdapter: EventAdapter
    companion object{
        const val event_id="package com.example.recycleview.Fragment.event_id"
        const val title="package com.example.recycleview.Fragment.Materiel_title"
        const val event_thum="package com.example.recycleview.Fragment.event_thum"
        const val event_description="package com.example.recycleview.Fragment.event_description"
        const val event_snippet="package com.example.recycleview.Fragment.event_snippet"


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        homeMvvm= ViewModelProviders.of(this)[EventModelView::class.java]
        popularItemsAdapter= EventAdapter()
    }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCategoriesBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareeventsRecycleView()
        homeMvvm.getMaterielByCategory()
        observeeventsliveData()
        oneventClick()
    }

    private fun oneventClick() {
        popularItemsAdapter.onItemclick = { event ->
            val intent = Intent(activity, eventsActivity::class.java)
            intent.putExtra(categoriesFragment.event_id, event._id)
            intent.putExtra(categoriesFragment.event_description, event.description)
            intent.putExtra(categoriesFragment.title, event.title)
            intent.putExtra(categoriesFragment.event_snippet, event.snippet)
            intent.putExtra(categoriesFragment.event_thum, event.eventImage)

            startActivity(intent)
        }
    }

    private fun prepareeventsRecycleView() {
        popularItemsAdapter= EventAdapter()
        binding.recView.apply {
            layoutManager= GridLayoutManager(context,3, GridLayoutManager.VERTICAL,false)
            adapter=popularItemsAdapter
        }     }

    private fun observeeventsliveData() {
        homeMvvm.observableMaterielLiveData().observe(viewLifecycleOwner, Observer{ events ->
            popularItemsAdapter.seteventList(events)



        })
    }


}