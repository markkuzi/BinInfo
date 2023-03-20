package com.example.bininfo.data.repository.datasourceimpl.bin

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.bininfo.data.localdb.BinDao
import com.example.bininfo.data.localdb.model.BinInfoModel
import com.example.bininfo.data.mapper.BinMapper
import com.example.bininfo.data.repository.datasource.bin.BinDataSource
import com.example.bininfo.domain.entities.BinList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BinDataSourceImpl(private val binDao: BinDao) :
    BinDataSource {

    private val mapper = BinMapper()

    override fun insert(binInfoModel: BinInfoModel) {
        CoroutineScope(Dispatchers.IO).launch {
            binDao.insert(binInfoModel)
        }
    }

    override fun getBinInfo(binId: String): LiveData<BinInfoModel> {
        return binDao.getBinInfo(binId)
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