package com.example.bininfo.data.repository.datasource.bin

import androidx.lifecycle.LiveData
import com.example.bininfo.data.localdb.model.BinInfoModel
import com.example.bininfo.domain.entities.BinList

interface BinDataSource {

    fun insert(binInfoModel: BinInfoModel)

    fun getBinInfo(binId: String): LiveData<BinInfoModel>

    fun loadBinList(): LiveData<List<BinList>>

    suspend fun deleteBinById(binId: String)
}