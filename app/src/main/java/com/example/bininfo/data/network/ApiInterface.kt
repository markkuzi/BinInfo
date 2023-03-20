package com.example.bininfo.data.network

import com.example.bininfo.data.network.model.BinInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("{bin}")
    suspend fun loadNewBin(
        @Path(PATH_PARAM_BIN) bin: String
    ): Response<BinInfoDto>?

    companion object {
        private const val PATH_PARAM_BIN = "bin"
    }
}