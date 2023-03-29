package com.example.bininfo.domain.entities

import com.example.bininfo.utils.Status

data class BinInfo(
    val binId: String?,
    val date: String,
    val status: Status,
    val numberLength: String?,
    val nuberLuhn: Boolean?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val countryName: String?,
    val countryEmoji: String?,
    val countryLatitude: String?,
    val countryLongitude: String?,
    val bankName: String?,
    val bankUrl: String?,
    val bankPhone: String?,
    val bankCity: String?
)
