package com.org.android.domain.network

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import com.org.android.data.models.PersonListRS
import com.org.android.presentation.utility.ApiConstant
import com.org.android.presentation.utility.AppConstant
import retrofit2.http.*

interface HomeApiService {

    @GET(ApiConstant.ApiGetUserList)
    suspend fun getUserList(@Query(AppConstant.page) page: Int): PersonListRS

}