package com.example.paging_practice.data.remote

import ListData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming

/**
 * 2023-09-07
 * pureum
 */
interface DataSource {

    @Streaming
    @GET("v2/list")
    suspend fun getBillList(
        @Query("page") page: Int?,
        @Query("limit") limit: Int? = 20,
    ): List<ListData>
}