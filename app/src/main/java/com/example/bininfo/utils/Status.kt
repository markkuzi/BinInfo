package com.example.bininfo.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Status: Parcelable {
    SUCCESS,
    NO_RESULT,
    ERROR,
    NONE
}