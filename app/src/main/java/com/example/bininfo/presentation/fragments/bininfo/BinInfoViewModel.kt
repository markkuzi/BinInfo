package com.example.bininfo.presentation.fragments.bininfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.usecase.BinUseCase
import kotlinx.coroutines.launch

class BinInfoViewModel(
    private val binUseCase: BinUseCase
): ViewModel() {

    private val _pendingStatus = MutableLiveData<Boolean?>()
    val pendingStatus: LiveData<Boolean?>
        get() = _pendingStatus

    fun loadNewBin(binId: String) = viewModelScope.launch {
        binUseCase.loadNewBin(binId)
    }

    fun getBinInfo(binId: String): LiveData<BinInfo> {
        return binUseCase.getBinInfo(binId)
    }

    fun deleteBinById(binId: String) = viewModelScope.launch {
        binUseCase.deleteBinById(binId)
    }

    fun getPendingStatus() {
        _pendingStatus.value = binUseCase.getPendingStatus()
    }

}