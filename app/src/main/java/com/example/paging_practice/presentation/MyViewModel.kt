package com.example.paging_practice.presentation

import ListData
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.paging_practice.data.remote.DataSourceImpl
import com.example.paging_practice.data.repository.MyRepository
import kotlinx.coroutines.flow.Flow

/**
 * 2023-09-09
 * pureum
 */
class MyViewModel(private val repository: MyRepository): ViewModel() {

    fun getList(): Flow<PagingData<ListData>> {
        return repository.getListRepo(DataSourceImpl())
    }


}