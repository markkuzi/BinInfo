package com.example.bininfo.data.repository.repository

import androidx.lifecycle.LiveData
import com.example.bininfo.data.mapper.BinMapper
import com.example.bininfo.data.repository.datasource.bin.BinApiDataSource
import com.example.bininfo.data.repository.datasource.bin.BinDataSource
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.entities.BinList
import com.example.bininfo.domain.repository.BinCall

class BinRepository(
    private val binDataSource: BinDataSource,
    private val binApiDataSource: BinApiDataSource
) : BinCall {

    override fun getBinInfo(binId: String): LiveData<BinInfo> {
        return binDataSource.getBinInfo(binId)
    }

    override suspend fun loadNewBin(binId: String) {
        binApiDataSource.loadNewBin(binId)
    }

    override fun getBinList(): LiveData<List<BinList>> {
        return binDataSource.loadBinList()
    }

    override fun getPendingStatus(): Boolean {
        return binApiDataSource.getPendingStatus()
    }

    override suspend fun deleteBinById(binId: String) {
        binDataSource.deleteBinById(binId)
    }
}