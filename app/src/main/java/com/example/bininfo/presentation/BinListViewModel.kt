package com.example.bininfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.domain.usecase.BinUseCase
import kotlinx.coroutines.launch

class BinListViewModel(
    private val binUseCase: BinUseCase
) : ViewModel() {

    val binHistoryList = binUseCase.getBinList()

    fun deleteBinById(binId: String) = viewModelScope.launch {
        binUseCase.deleteBinById(binId)
    }

}