package com.org.android.data.contract

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import com.org.android.data.Either
import com.org.android.data.models.*
import com.org.android.domain.exceptions.MyException

interface HomeRepo {
    suspend fun getPersonList(userListPRQ: PersonListPRQ): Either<MyException, PersonListRS>
}