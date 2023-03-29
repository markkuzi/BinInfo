package com.example.bininfo.domain.usecase

import androidx.lifecycle.LiveData
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.entities.BinList
import com.example.bininfo.domain.repository.BinCall
import com.example.bininfo.utils.NetworkResult

class BinUseCase(private val binCall: BinCall) {

    suspend fun getBinInfo(binId: String): BinInfo {
        return binCall.getBinInfo(binId)
    }

    suspend fun loadNewBin(binId: String) {
        return binCall.loadNewBin(binId)
    }

    fun getBinList(): LiveData<List<BinList>> {
        return binCall.getBinList()
    }

    suspend fun deleteBinById(binId: String) {
        binCall.deleteBinById(binId)
    }

    fun getResult(): LiveData<NetworkResult<BinInfo>> {
        return binCall.getResult()
    }
}