package com.example.bininfo.domain.repository

import androidx.lifecycle.LiveData
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.entities.BinList
import com.example.bininfo.utils.NetworkResult

interface BinCall {

    suspend fun getBinInfo(binId: String): BinInfo

    suspend fun loadNewBin(binId: String)

    fun getBinList(): LiveData<List<BinList>>

    fun getResult(): LiveData<NetworkResult<BinInfo>>

    suspend fun deleteBinById(binId: String)

}