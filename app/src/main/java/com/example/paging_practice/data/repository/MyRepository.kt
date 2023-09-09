package com.example.paging_practice.data.repository

import ListData
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging_practice.data.paging.MyPagingSource
import com.example.paging_practice.data.remote.DataSourceImpl
import kotlinx.coroutines.flow.Flow

/**
 * 2023-09-09
 * pureum
 */
class MyRepository {

    fun getListRepo(dataSourceImpl: DataSourceImpl): Flow<PagingData<ListData>> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { MyPagingSource(dataSourceImpl) }
        ).flow
    }
}