package com.example.bininfo.domain.usecase

import androidx.lifecycle.LiveData
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.repository.BinCall
import com.example.bininfo.utils.NetworkResult

class GetNetworkResultUseCase(private val binCall: BinCall) {

    operator fun invoke(): LiveData<NetworkResult<BinInfo>> {
        return binCall.getResult()
    }
}