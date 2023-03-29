package com.example.bininfo.data.repository.datasource.bin

import androidx.lifecycle.LiveData
import com.example.bininfo.data.localdb.model.BinInfoDbModel
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.entities.BinList

interface BinDataSource {

    suspend fun insert(binInfoDbModel: BinInfoDbModel)

    suspend fun getBinInfo(binId: String): BinInfo

    fun loadBinList(): LiveData<List<BinList>>

    suspend fun deleteBinById(binId: String)
}