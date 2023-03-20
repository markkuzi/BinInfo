package com.example.bininfo.data.localdb.model

import com.example.bininfo.domain.entities.Status

data class BinListDb(
    val binId: String,
    val date: Long,
    val status: Status
)
