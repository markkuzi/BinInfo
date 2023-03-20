package com.example.bininfo.presentation.fragments.bininfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.data.localdb.model.BinInfoModel
import com.example.bininfo.domain.usecase.BinUseCase
import kotlinx.coroutines.launch

class BinInfoViewModel(
    private val binUseCase: BinUseCase
) : ViewModel() {

    fun getBinInfo(binId: String): LiveData<BinInfoModel> {
        return binUseCase.getBinInfo(binId)
    }

    fun loadNewBin(binId: String) = viewModelScope.launch {
        binUseCase.loadNewBin(binId)
    }

    fun deleteBinById(binId: String) = viewModelScope.launch {
        binUseCase.deleteBinById(binId)
    }

    val pendingStatus = binUseCase.getPendingStatus()


}