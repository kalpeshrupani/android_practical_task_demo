package com.org.android.di

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import com.org.android.data.contract.HomeRepo
import com.org.android.data.repository.HomeRepository
import org.koin.dsl.module.module

/**
 * Created Koin module for Repository class
 */

val repositoryModule = module {
    single<HomeRepo> { HomeRepository(get(), get()) }
}