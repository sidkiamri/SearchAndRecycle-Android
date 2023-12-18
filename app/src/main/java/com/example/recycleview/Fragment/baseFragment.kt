package com.example.recycleview.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recycleview.activity.LoginActivity
import com.example.recycleview.activity.RegisterActivity
import com.example.recycleview.databinding.FragmentBaseBinding

class baseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentBaseBinding.inflate(inflater, container, false);
        binding.btnConnexion.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        binding.btnInscription.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
        }




        return binding.root;

    }


}