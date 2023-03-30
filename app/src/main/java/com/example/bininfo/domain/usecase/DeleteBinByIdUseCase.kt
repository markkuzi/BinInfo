package com.example.bininfo.domain.usecase

import com.example.bininfo.domain.repository.BinCall

class DeleteBinByIdUseCase(private val binCall: BinCall) {

    suspend operator fun invoke(binId: String) {
        binCall.deleteBinById(binId)
    }
}