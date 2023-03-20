package com.example.bininfo.data.repository.datasource.bin

import androidx.lifecycle.LiveData
import com.example.bininfo.data.localdb.model.BinInfoDbModel
import com.example.bininfo.data.localdb.model.BinListDb
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.entities.BinList

interface BinDataSource {

    fun insert(binInfoDbModel: BinInfoDbModel)

    fun getBinInfo(binId: String): LiveData<BinInfo>

    fun loadBinList(): LiveData<List<BinList>>

    suspend fun deleteBinById(binId: String)
}