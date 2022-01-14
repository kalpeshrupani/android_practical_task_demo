package com.org.android.data.models

import com.google.gson.annotations.SerializedName

data class PersonListPRQ(
    @SerializedName("page")
    var page: Int
)