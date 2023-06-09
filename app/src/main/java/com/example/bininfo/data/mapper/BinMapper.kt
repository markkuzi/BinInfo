package com.example.bininfo.data.mapper

import com.example.bininfo.data.localdb.model.BinInfoDbModel
import com.example.bininfo.data.localdb.model.BinListDb
import com.example.bininfo.data.network.model.BinInfoDto
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.entities.BinList
import com.example.bininfo.utils.Status
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BinMapper {

    fun mapDtoToDbModel(dto: BinInfoDto?, binId: String) = BinInfoDbModel(
        binId = binId,
        date = getTime(),
        status = Status.SUCCESS,
        numberLength = dto?.number?.length ?: RESULT_IF_NULL,
        numberLuhn = dto?.number?.luhn,
        scheme = dto?.scheme ?: RESULT_IF_NULL,
        type = dto?.type,
        brand = dto?.brand ?: RESULT_IF_NULL,
        prepaid = dto?.prepaid,
        countryName = dto?.country?.name ?: RESULT_IF_NULL,
        countryEmoji = dto?.country?.emoji ?: RESULT_IF_NULL,
        countryLatitude = dto?.country?.latitude ?: RESULT_IF_NULL,
        countryLongitude = dto?.country?.longitude ?: RESULT_IF_NULL,
        bankName = dto?.bank?.name ?: RESULT_IF_NULL,
        bankUrl = dto?.bank?.url ?: RESULT_IF_NULL,
        bankPhone = dto?.bank?.phone ?: RESULT_IF_NULL,
        bankCity = dto?.bank?.city ?: RESULT_IF_NULL
    )

    fun mapDtoToDbModelIfEmpty(binId: String) = BinInfoDbModel(
        binId = binId,
        date = getTime(),
        status = Status.NO_RESULT,
        numberLength = null,
        numberLuhn = null,
        scheme = null,
        type = null,
        brand = null,
        prepaid = null,
        countryName = null,
        countryEmoji = null,
        countryLatitude = null,
        countryLongitude = null,
        bankName = null,
        bankUrl = null,
        bankPhone = null,
        bankCity = null
    )

    fun mapDtoToDbModelIfError(binId: String) = BinInfoDbModel(
        binId = binId,
        date = getTime(),
        status = Status.ERROR,
        numberLength = null,
        numberLuhn = null,
        scheme = null,
        type = null,
        brand = null,
        prepaid = null,
        countryName = null,
        countryEmoji = null,
        countryLatitude = null,
        countryLongitude = null,
        bankName = null,
        bankUrl = null,
        bankPhone = null,
        bankCity = null
    )

    fun mapDtoToEntityModel(dto: BinInfoDto, binId: String) = BinInfo(
        binId = binId,
        date = convertTimestampToTime(getTime()),
        status = Status.SUCCESS,
        numberLength = dto.number?.length ?: RESULT_IF_NULL,
        numberLuhn = dto.number?.luhn,
        scheme = dto.scheme ?: RESULT_IF_NULL,
        type = dto.type,
        brand = dto.brand ?: RESULT_IF_NULL,
        prepaid = dto.prepaid,
        countryName = dto.country?.name ?: RESULT_IF_NULL,
        countryEmoji = dto.country?.emoji ?: RESULT_IF_NULL,
        countryLatitude = dto.country?.latitude ?: RESULT_IF_NULL,
        countryLongitude = dto.country?.longitude ?: RESULT_IF_NULL,
        bankName = dto.bank?.name ?: RESULT_IF_NULL,
        bankUrl = dto.bank?.url ?: RESULT_IF_NULL,
        bankPhone = dto.bank?.phone ?: RESULT_IF_NULL,
        bankCity = dto.bank?.city ?: RESULT_IF_NULL
    )

    fun mapDbToEntityBinInfo(binInfoDbDbModel: BinInfoDbModel?) = BinInfo(
        binId = binInfoDbDbModel?.binId,
        date = convertTimestampToTime(binInfoDbDbModel?.date),
        status = Status.SUCCESS,
        numberLength = binInfoDbDbModel?.numberLength,
        numberLuhn = binInfoDbDbModel?.numberLuhn,
        scheme = binInfoDbDbModel?.scheme,
        type = binInfoDbDbModel?.type,
        brand = binInfoDbDbModel?.brand,
        prepaid = binInfoDbDbModel?.prepaid,
        countryName = binInfoDbDbModel?.countryName,
        countryEmoji = binInfoDbDbModel?.countryEmoji,
        countryLatitude = binInfoDbDbModel?.countryLatitude,
        countryLongitude = binInfoDbDbModel?.countryLongitude,
        bankName = binInfoDbDbModel?.bankName,
        bankUrl = binInfoDbDbModel?.bankUrl,
        bankPhone = binInfoDbDbModel?.bankPhone,
        bankCity = binInfoDbDbModel?.bankCity
    )

    fun mapDbToEntityBinList(dbModel: BinListDb) = BinList(
        binId = dbModel.binId,
        date = convertTimestampToTime(dbModel.date),
        status = dbModel.status
    )

    private fun getTime(): Long {
        val date = Calendar.getInstance()
        return date.timeInMillis
    }

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return RESULT_IF_NULL
        val stamp = Timestamp(timestamp)
        val date = Date(stamp.time)
        val pattern = DATE_FORMAT
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        private const val DATE_FORMAT = "dd.MM.yyyy, HH:mm"
        private const val RESULT_IF_NULL = "?"
    }

}