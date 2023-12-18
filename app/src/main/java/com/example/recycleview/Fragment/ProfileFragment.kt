package com.example.recycleview.Fragment


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.recycleview.activity.*
import com.example.recycleview.databinding.FragmentProfileBinding
import com.example.recycleview.pojo.user
import com.example.recycleview.retrofit.sessionManager
import com.example.recycleview.viewModel.ProfileViewModel


class ProfileFragment : Fragment() {
    private lateinit var randomMateriel: user
    private val sharedPrefFile = "kotlinsharedpreference"

    lateinit var binding: FragmentProfileBinding
    private lateinit var Profile: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Profile = ViewModelProviders.of(this)[ProfileViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Profile.loadProfile()
        observerRandomuser()
        binding.logo.setOnClickListener {
            val appContext = requireContext().applicationContext

            sessionManager.clearData(appContext)
            requireActivity().run {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }


    private fun observerRandomuser() {

        Profile.observerDetailsLiveData().observe(
            viewLifecycleOwner
        ) { Materiel ->
            Glide.with(this)
                .load(Materiel!!.avatar)
                .into(binding.imgProfile)
            binding.txtUsername.text = Materiel.FirstName
            binding.email.text = Materiel.email
            binding.designation.text = Materiel.LastName
            binding.txtUsername.text = Materiel.FirstName
            binding.country.text = Materiel.country
            binding.address.text = Materiel.address
            binding.code.text = Materiel.codePostal.toString()
            binding.points.text = Materiel.points.toString()

            val prefs = this.activity?.getSharedPreferences("Login",Context.MODE_PRIVATE)
            val sp = prefs?.edit()
            sp?.putInt("pts", Materiel.points)
            sp?.commit()



        }
    }
}