package com.example.bininfo.data.repository.datasourceimpl.bin

import com.example.bininfo.data.localdb.model.BinInfoDbModel
import com.example.bininfo.data.mapper.BinMapper
import com.example.bininfo.data.network.ApiFactory
import com.example.bininfo.data.repository.datasource.bin.BinApiDataSource
import com.example.bininfo.data.repository.datasource.bin.BinDataSource

class BinApiDataSourceImpl(
    private val binDataSource: BinDataSource
) : BinApiDataSource {

    private val mapper = BinMapper()
    private var pendingStatus: Boolean = false

    override suspend fun loadNewBin(binId: String) {
        pendingStatus = true

        val binInfo: BinInfoDbModel = try {
            val response = ApiFactory.apiService?.loadNewBin(binId)
            if (response?.isSuccessful!! && response.body() != null) {
                mapper.mapDtoToDbModel(response.body()!!, binId)

            } else {
                mapper.mapDbToModelIfNoResult(binId)
            }
        } catch (e: Exception) {
            mapper.mapDbToModelIfError(binId)
        }
        binDataSource.insert(binInfo)
        pendingStatus = false

    }

    override fun getPendingStatus(): Boolean {
        return pendingStatus
    }
}