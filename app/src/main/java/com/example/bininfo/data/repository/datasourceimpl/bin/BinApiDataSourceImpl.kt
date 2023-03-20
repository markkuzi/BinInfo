package com.example.bininfo.data.repository.datasourceimpl.bin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bininfo.data.localdb.model.BinInfoModel
import com.example.bininfo.data.mapper.BinMapper
import com.example.bininfo.data.network.ApiFactory
import com.example.bininfo.data.repository.datasource.bin.BinApiDataSource
import com.example.bininfo.data.repository.datasource.bin.BinDataSource

class BinApiDataSourceImpl(
    private val binDataSource: BinDataSource
) : BinApiDataSource {

    private val mapper = BinMapper()
    private var _pendingStatus = MutableLiveData<Boolean>()
    private val pendingStatus: LiveData<Boolean> = _pendingStatus

    private var _errorStatus = MutableLiveData<Boolean>()
    private val errorStatus: LiveData<Boolean> = _errorStatus

    override suspend fun loadNewBin(binId: String) {
        _pendingStatus.postValue(false)
        _errorStatus.postValue(false)

        var binInfo: BinInfoModel
        try {
            val response = ApiFactory.apiService?.loadNewBin(binId)
            binInfo = if (response?.isSuccessful!! && response.body() != null) {
                mapper.mapDtoToDbModel(response.body()!!, binId)

            } else {
                mapper.mapDbToModelIfNoResult(binId)
            }
        } catch (e: Exception) {
            binInfo = mapper.mapDbToModelIfError(binId)
            _errorStatus.postValue(true)
        }
        binDataSource.insert(binInfo)
        _pendingStatus.postValue(true)

    }

    override fun getPendingStatus(): LiveData<Boolean> {
        return pendingStatus
    }
}