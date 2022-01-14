package com.org.android.di

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import com.org.android.presentation.home.HomeViewModel
import com.org.android.presentation.splash.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created Koin module for ViewModel class
 */
val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}