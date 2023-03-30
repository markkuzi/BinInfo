package com.example.bininfo.presentation.fragments.bininfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.domain.usecase.DeleteBinByIdUseCase
import com.example.bininfo.domain.usecase.GetBinInfoUseCase
import com.example.bininfo.domain.usecase.GetNetworkResultUseCase
import com.example.bininfo.domain.usecase.LoadNewBinUseCase
import kotlinx.coroutines.launch

class BinInfoViewModel(
    private val getNetworkResultUseCase: GetNetworkResultUseCase,
    private val getBinInfoUseCase: GetBinInfoUseCase,
    private val loadNewBinUseCase: LoadNewBinUseCase,
    private val deleteBinByIdUseCase: DeleteBinByIdUseCase
) : ViewModel() {

    private val _binInfo = MutableLiveData<BinInfo>()
    val binInfo: LiveData<BinInfo>
        get() = _binInfo

    val binNewInfo by lazy { getNetworkResultUseCase() }

    fun getBinInfo(binId: String) = viewModelScope.launch {
        _binInfo.postValue(getBinInfoUseCase.invoke(binId))
    }

    fun loadNewBin(binId: String) = viewModelScope.launch {
        loadNewBinUseCase(binId)
    }

    fun deleteBinById(binId: String) = viewModelScope.launch {
        deleteBinByIdUseCase(binId)
    }


}