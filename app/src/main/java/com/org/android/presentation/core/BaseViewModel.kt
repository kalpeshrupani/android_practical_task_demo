package com.org.android.presentation.core

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.org.android.data.Either
import com.org.android.domain.exceptions.MyException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    val exceptionLiveData: MutableLiveData<Exception> = MutableLiveData()

    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


    /**
     * For separate the success and exception from network call
     *
     * @param T - Generic type of your class
     * @param either - your either which have success or exception
     * @param successLiveData - your success livedata
     */
    fun <T> postValue(either: Either<MyException, T>, successLiveData: MutableLiveData<T>) {
        either.either({
            exceptionLiveData.postValue(it)
        }, {
            successLiveData.postValue(it)
        })
    }
}