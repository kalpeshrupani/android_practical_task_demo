package com.org.android.di

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import androidx.room.Room
import com.org.android.data.database.AppDatabase
import com.org.android.presentation.utility.AppConstant
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module


val roomModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            AppConstant.DB_NAME
        ).build()
    }

    single { get<AppDatabase>().appDao() }

}