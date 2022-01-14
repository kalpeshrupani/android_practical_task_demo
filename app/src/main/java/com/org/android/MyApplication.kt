package com.org.android

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */

import android.app.Application
import android.content.Context
import com.org.android.di.*
import org.koin.android.ext.android.startKoin

open class MyApplication : Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {

        context = this

        startKoin(
            this, arrayListOf(
                networkModule,
                repositoryModule,
                viewModelModule,
                roomModule
            )
        )
    }
}