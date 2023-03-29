package com.example.bininfo.presentation.fragments.bininfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.domain.usecase.BinUseCase
import kotlinx.coroutines.launch

class BinInfoViewModel(
    private val binUseCase: BinUseCase
) : ViewModel() {

    val binInfo = binUseCase.getResult()

    fun loadNewBin(binId: String) = viewModelScope.launch {
        binUseCase.loadNewBin(binId)
    }

    fun deleteBinById(binId: String) = viewModelScope.launch {
        binUseCase.deleteBinById(binId)
    }


}