package com.example.bininfo.data.repository.datasource.bin

import androidx.lifecycle.LiveData

interface BinApiDataSource {

    suspend fun loadNewBin(binId: String)

    fun getPendingStatus(): LiveData<Boolean>

}