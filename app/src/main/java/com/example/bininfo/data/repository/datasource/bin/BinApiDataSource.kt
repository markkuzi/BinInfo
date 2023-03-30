package com.example.bininfo.data.repository.datasource.bin

import androidx.lifecycle.LiveData
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.utils.NetworkResult

interface BinApiDataSource {

    suspend fun loadNewBin(binId: String)

    fun getResult() : LiveData<NetworkResult<BinInfo>>

}