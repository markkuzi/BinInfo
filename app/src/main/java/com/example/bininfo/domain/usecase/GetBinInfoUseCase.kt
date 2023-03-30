package com.example.bininfo.domain.usecase

import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.repository.BinCall

class GetBinInfoUseCase(private val binCall: BinCall) {

    suspend operator fun invoke(binId: String): BinInfo {
        return binCall.getBinInfo(binId)
    }
}