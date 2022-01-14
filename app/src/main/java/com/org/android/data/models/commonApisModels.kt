package com.org.android.data.models

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.org.android.presentation.utility.AppConstant
import com.google.gson.annotations.SerializedName


data class PersonListRS(
    @SerializedName(AppConstant.data)
    var personList: List<Person>? = arrayListOf(),
    @SerializedName(AppConstant.page)
    var page: Int? = 0,
    @SerializedName(AppConstant.perPage)
    var perPage: Int? = 0,
    @SerializedName(AppConstant.total)
    var total: Int? = 0,
    @SerializedName(AppConstant.totalPages)
    var totalPages: Int? = 0
) : BaseResponse()

@Entity(tableName = AppConstant.TABLE_NAME_USER)
data class Person(

    @PrimaryKey(autoGenerate = false)
    @SerializedName(AppConstant.id) var id: Int = 0,

    @ColumnInfo(name = AppConstant.avatar)
    @SerializedName(AppConstant.avatar) var avatar: String? = "",

    @ColumnInfo(name = AppConstant.email)
    @SerializedName(AppConstant.email) var email: String? = "",

    @ColumnInfo(name = AppConstant.firstName)
    @SerializedName(AppConstant.firstName) var firstName: String? = "",

    @ColumnInfo(name = AppConstant.lastName)
    @SerializedName(AppConstant.lastName) var lastName: String? = ""
)
