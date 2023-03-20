package com.example.bininfo.domain.repository

import androidx.lifecycle.LiveData
import com.example.bininfo.data.localdb.model.BinInfoModel
import com.example.bininfo.domain.entities.BinList

interface BinCall {

    fun getBinInfo(binId: String): LiveData<BinInfoModel>

    suspend fun loadNewBin(binId: String)

    fun getBinList(): LiveData<List<BinList>>

    fun getPendingStatus(): LiveData<Boolean>

    suspend fun deleteBinById(binId: String)

}