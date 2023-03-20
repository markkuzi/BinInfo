package com.example.bininfo.data.repository.datasource.bin

interface BinApiDataSource {

    suspend fun loadNewBin(binId: String)

    fun getPendingStatus(): Boolean
}