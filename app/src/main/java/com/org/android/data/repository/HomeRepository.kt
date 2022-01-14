package com.org.android.data.repository

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */

import com.org.android.data.BaseRepository
import com.org.android.data.Either
import com.org.android.data.contract.HomeRepo
import com.org.android.data.database.AppDao
import com.org.android.data.models.*
import com.org.android.domain.exceptions.MyException
import com.org.android.domain.network.HomeApiService

class HomeRepository constructor(
    private val homeApiService: HomeApiService,
    private val appDao: AppDao
) : HomeRepo, BaseRepository() {

    override suspend fun getPersonList(userListPRQ: PersonListPRQ): Either<MyException, PersonListRS> {
        return either {
            homeApiService.getUserList(
                userListPRQ.page
            )
        }
    }
}