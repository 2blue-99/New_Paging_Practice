package com.example.paging_practice.data.paging

import ListData
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging_practice.data.remote.DataSourceImpl
import com.example.paging_practice.data.service.Remote

/**
 * 2023-09-07
 * pureum
 */
class MyPagingSource(private val dataSourceImpl: DataSourceImpl): PagingSource<Int, ListData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListData> {
        return try {
            val page = params.key?: 0
            val results = dataSourceImpl.getBillList(page).toMutableList()
            val nextPage = if(results.count() == 20) page + 1 else null
            LoadResult.Page(data = results, nextKey = nextPage, prevKey = null).also {
                Log.e(
                    "TAG",
                    "load: $it",
                ) }
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, ListData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}