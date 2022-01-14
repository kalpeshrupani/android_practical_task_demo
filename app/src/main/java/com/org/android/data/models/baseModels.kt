package com.org.android.data.models

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import com.org.android.presentation.utility.AppConstant
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class BaseResponse(
    @Expose
    @SerializedName("status")
    var status: Int? = 0,
    @Expose
    @SerializedName("message")
    var message: String? = ""
) {
    fun isSuccess(): Boolean {
        return status == 1
    }
}