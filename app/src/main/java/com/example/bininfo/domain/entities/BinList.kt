package com.example.bininfo.domain.entities

import com.example.bininfo.utils.Status

data class BinList(
    val binId: String,
    val date: String,
    val status: Status
)
