package com.example.bininfo.presentation.di

import androidx.lifecycle.ViewModel
import com.example.bininfo.domain.usecase.BinUseCase

class BinListViewModel(
    private val binUseCase: BinUseCase
): ViewModel() {
}