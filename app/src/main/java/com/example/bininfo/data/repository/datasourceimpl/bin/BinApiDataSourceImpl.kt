package com.example.bininfo.data.repository.datasourceimpl.bin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bininfo.data.mapper.BinMapper
import com.example.bininfo.data.network.ApiFactory
import com.example.bininfo.data.repository.datasource.bin.BinApiDataSource
import com.example.bininfo.data.repository.datasource.bin.BinDataSource
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.utils.NetworkResult
import com.example.bininfo.utils.Status

class BinApiDataSourceImpl(
    private val binDataSource: BinDataSource
) : BinApiDataSource {

    private val mapper = BinMapper()

    private val _binInfo = MutableLiveData<NetworkResult<BinInfo>>()
    val binInfo: LiveData<NetworkResult<BinInfo>>
        get() = _binInfo

    override suspend fun loadNewBin(binId: String) {

        _binInfo.value = NetworkResult.Loading()
        try {
            val response = ApiFactory.apiService?.loadNewBin(binId)
            if (response?.isSuccessful!! && response.body() != null) {
                response.body().let { res ->
                    val data = res?.let { mapper.mapDtoToEntityModel(it, binId) }
                    _binInfo.value = NetworkResult.Success(data)
                }
            } else {
                _binInfo.value = NetworkResult.Error(Status.NO_RESULT)
            }
        } catch (e: Exception) {
            _binInfo.value = NetworkResult.Error(Status.ERROR)
        }
    }

    override fun getResult(): LiveData<NetworkResult<BinInfo>> {
        return binInfo
    }
}
