package com.example.bininfo.data.localdb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bininfo.utils.Status

@Entity(tableName = "bin_info_list")
data class BinInfoDbModel(
    @PrimaryKey
    @ColumnInfo(name = "binId")
    val binId: String,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "status")
    val status: Status,
    @ColumnInfo(name = "numberLength")
    val numberLength: String?,
    @ColumnInfo(name = "nuberLuhn")
    val numberLuhn: Boolean?,
    @ColumnInfo(name = "scheme")
    val scheme: String?,
    @ColumnInfo(name = "type")
    val type: String?,
    @ColumnInfo(name = "brand")
    val brand: String?,
    @ColumnInfo(name = "prepaid")
    val prepaid: Boolean?,
    @ColumnInfo(name = "countryName")
    val countryName: String?,
    @ColumnInfo(name = "countryEmoji")
    val countryEmoji: String?,
    @ColumnInfo(name = "countryLatitude")
    val countryLatitude: String?,
    @ColumnInfo(name = "countryLongitude")
    val countryLongitude: String?,
    @ColumnInfo(name = "bankName")
    val bankName: String?,
    @ColumnInfo(name = "bankUrl")
    val bankUrl: String?,
    @ColumnInfo(name = "bankPhone")
    val bankPhone: String?,
    @ColumnInfo(name = "bankCity")
    val bankCity: String?
)