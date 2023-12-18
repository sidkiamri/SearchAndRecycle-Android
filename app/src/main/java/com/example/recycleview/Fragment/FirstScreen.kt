package com.example.recycleview.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.recycleview.R
import com.example.recycleview.databinding.ActivityScreen1Binding
import com.example.recycleview.databinding.FragmentFirstBinding


class FirstScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = ActivityScreen1Binding.inflate(inflater,container,false);

        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

    /* binding.nextImgButton.setOnClickListener {
            viewPager?.currentItem = 1                     // 0-indexing, 1 is actually the 2nd screen
        }*/

        return binding.root;
    }
}