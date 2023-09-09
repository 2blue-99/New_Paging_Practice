package com.example.paging_practice.data.remote

import ListData
import android.util.Log
import com.example.paging_practice.data.service.Remote

/**
 * 2023-09-07
 * pureum
 */
class DataSourceImpl: DataSource {

    override suspend fun getBillList(page: Int?, limit: Int?): List<ListData> {
        return Remote.serviceRetrofit().create(DataSource::class.java).getBillList(page, limit)
    }
}