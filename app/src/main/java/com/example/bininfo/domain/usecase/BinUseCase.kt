package com.example.bininfo.domain.usecase

import androidx.lifecycle.LiveData
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.entities.BinList
import com.example.bininfo.domain.repository.BinCall

class BinUseCase(private val binCall: BinCall) {

    fun getBinInfo(binId: String): LiveData<BinInfo> {
        return binCall.getBinInfo(binId)
    }

    suspend fun loadNewBin(binId: String) {
        binCall.loadNewBin(binId)
    }

    fun getBinList(): LiveData<List<BinList>> {
        return binCall.getBinList()
    }

    fun getPendingStatus(): Boolean? {
        return binCall.getPendingStatus()
    }

    suspend fun deleteBinById(binId: String) {
        binCall.deleteBinById(binId)
    }
}