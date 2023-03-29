package com.example.bininfo.data.repository.datasourceimpl.bin

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.bininfo.data.localdb.BinDao
import com.example.bininfo.data.localdb.model.BinInfoDbModel
import com.example.bininfo.data.mapper.BinMapper
import com.example.bininfo.data.repository.datasource.bin.BinDataSource
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.entities.BinList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BinDataSourceImpl(private val binDao: BinDao) :
    BinDataSource {

    private val mapper = BinMapper()

    override suspend fun insert(binInfoDbModel: BinInfoDbModel) {
        CoroutineScope(Dispatchers.IO).launch {
            binDao.insert(binInfoDbModel)
        }
    }

    override suspend fun getBinInfo(binId: String): BinInfo {
        return mapper.mapDbToEntityBinInfo(binDao.getBinInfo(binId))
    }

    override fun loadBinList(): LiveData<List<BinList>> {
        return Transformations.map(binDao.getBinInfoList()) {
            it.map { dbList ->
                mapper.mapDbToEntityBinList(dbList)
            }
        }
    }

    override suspend fun deleteBinById(binId: String) {
        binDao.deleteBinById(binId)
    }

}