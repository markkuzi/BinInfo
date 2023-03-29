package com.example.bininfo.data.localdb.model

import com.example.bininfo.utils.Status

data class BinListDb(
    val binId: String,
    val date: Long,
    val status: Status
)
