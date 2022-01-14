package com.org.android.presentation.home.activities

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import androidx.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import com.org.android.R
import com.org.android.data.models.Person
import com.org.android.databinding.ActivityHomeBinding
import com.org.android.presentation.core.BaseActivity
import com.org.android.presentation.home.HomeViewModel
import com.org.android.presentation.utility.IntentHelper

import com.org.android.presentation.utility.isNetworkAvailable
import com.org.android.presentation.utility.startActivityCustom
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun getBaseViewModel() = homeViewModel

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setupToolbar(
            binding.llToolbarMain.toolbar,
            getString(R.string.app_name),
            false,
            Color.BLACK
        )
        initClickListener()
        attachObserver()

    }

    private fun initClickListener() {
        binding.btnPracticalTaskSecond.setOnClickListener {
            startActivityCustom(IntentHelper.getTaskSecondScreenIntent(this, true))
        }
    }

    private fun attachObserver() {

    }


}
