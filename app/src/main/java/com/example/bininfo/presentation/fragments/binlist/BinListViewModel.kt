package com.example.bininfo.presentation.fragments.binlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.domain.usecase.DeleteBinByIdUseCase
import com.example.bininfo.domain.usecase.GetBinListUseCase
import kotlinx.coroutines.launch

class BinListViewModel(
    private val getBinListUseCase: GetBinListUseCase,
    private val deleteBinByIdUseCase: DeleteBinByIdUseCase
) : ViewModel() {

    val binHistoryList by lazy { getBinListUseCase() }

    fun deleteBinById(binId: String) = viewModelScope.launch {
        deleteBinByIdUseCase(binId)
    }

}