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
) : ViewModel() {

    private val _binInfo = MutableLiveData<BinInfo>()
    val binInfo: LiveData<BinInfo>
        get() = _binInfo

    val binNewInfo = binUseCase.getResult()

    fun getBinInfo(binId: String) = viewModelScope.launch {
        _binInfo.postValue(binUseCase.getBinInfo(binId))
    }

    fun loadNewBin(binId: String) = viewModelScope.launch {
        binUseCase.loadNewBin(binId)
    }

    fun deleteBinById(binId: String) = viewModelScope.launch {
        binUseCase.deleteBinById(binId)
    }


}