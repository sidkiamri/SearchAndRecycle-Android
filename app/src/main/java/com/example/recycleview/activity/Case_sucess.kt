package com.example.recycleview.activity

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleview.databinding.ActivityCaseSucessBinding
import com.example.recycleview.viewModel.RewardViewModel


class Case_sucess: AppCompatActivity() {

    private lateinit var binding: ActivityCaseSucessBinding
    private val viewModel by viewModels<RewardViewModel>()

}