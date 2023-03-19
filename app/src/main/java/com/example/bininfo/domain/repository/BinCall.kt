package com.example.bininfo.domain.repository

import androidx.lifecycle.LiveData
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.entities.BinList

interface BinCall {

    fun getBinInfo(binId:String): LiveData<BinInfo>

    suspend fun loadNewBin(binId: String)

    fun getBinList(): LiveData<List<BinList>>

    fun getPendingStatus(): Boolean?

    suspend fun deleteBinById(binId: String)

}