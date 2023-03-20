package com.example.bininfo.data.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://lookup.binlist.net/"

    private val moshi = Moshi.Builder().build()

    private val moshiConverterFactory = MoshiConverterFactory.create(moshi)

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(moshiConverterFactory)
        .build()

    val apiService: ApiInterface? = retrofit.create(ApiInterface::class.java)


}