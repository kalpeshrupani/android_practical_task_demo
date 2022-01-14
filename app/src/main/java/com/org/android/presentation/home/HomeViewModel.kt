package com.org.android.presentation.home

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import androidx.lifecycle.MutableLiveData
import com.org.android.data.contract.HomeRepo
import com.org.android.data.models.*
import com.org.android.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val homeRepo: HomeRepo) : BaseViewModel() {

    val personListRSLiveData: MutableLiveData<PersonListRS> = MutableLiveData()

    fun getPersonList(page: Int) {
        launch {
            val userListPRQ = PersonListPRQ(page)
            postValue(homeRepo.getPersonList(userListPRQ), personListRSLiveData)
        }
    }
}