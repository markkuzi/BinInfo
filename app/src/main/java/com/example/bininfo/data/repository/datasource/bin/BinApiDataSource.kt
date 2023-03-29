package com.example.bininfo.data.repository.datasource.bin

import androidx.lifecycle.LiveData
import com.example.bininfo.data.network.model.BinInfoDto
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.utils.NetworkResult
import retrofit2.Response

interface BinApiDataSource {

    suspend fun loadNewBin(binId: String)

    fun getResult() : LiveData<NetworkResult<BinInfo>>

}