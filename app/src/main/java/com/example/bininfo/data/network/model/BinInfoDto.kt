package com.example.bininfo.data.network.model

data class BinInfoDto (
    val number: NumberDto?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryDto?,
    val bank: BankDto?,
)