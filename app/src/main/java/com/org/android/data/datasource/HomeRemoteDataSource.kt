package com.org.android.data.datasource

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import com.org.android.data.Either
import com.org.android.data.models.PersonListPRQ
import com.org.android.data.models.PersonListRS
import com.org.android.domain.exceptions.MyException

interface HomeRemoteDataSource {
    suspend fun getPersonList(personListPRQ: PersonListPRQ): Either<MyException, PersonListRS>
}