package com.org.android.data.models


import android.util.TypedValue
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.org.android.domain.network.HomeApiService
import retrofit2.HttpException
import java.io.IOException


class UserPagingSource : PagingSource<Int, Person>() {
    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        return try {
            val nextPage = params.key ?: 1
            val userList = RestClient().getService().getUserList(page = nextPage)
            LoadResult.Page(
                data = userList.personList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.personList.isEmpty()) null else userList.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}