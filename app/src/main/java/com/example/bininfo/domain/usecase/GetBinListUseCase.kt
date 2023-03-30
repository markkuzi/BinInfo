package com.example.bininfo.domain.usecase

import androidx.lifecycle.LiveData
import com.example.bininfo.domain.entities.BinList
import com.example.bininfo.domain.repository.BinCall

class GetBinListUseCase(private val binCall: BinCall) {

    operator fun invoke(): LiveData<List<BinList>> {
        return binCall.getBinList()
    }
}