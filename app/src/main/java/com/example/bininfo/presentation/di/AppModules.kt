package com.example.bininfo.presentation.di

import androidx.room.Room
import com.example.bininfo.data.localdb.BinDB
import com.example.bininfo.data.repository.datasource.bin.BinApiDataSource
import com.example.bininfo.data.repository.datasource.bin.BinDataSource
import com.example.bininfo.data.repository.datasourceimpl.bin.BinApiDataSourceImpl
import com.example.bininfo.data.repository.datasourceimpl.bin.BinDataSourceImpl
import com.example.bininfo.data.repository.repository.BinRepository
import com.example.bininfo.domain.repository.BinCall
import com.example.bininfo.domain.usecase.BinUseCase
import com.example.bininfo.presentation.BinInfoViewModel
import com.example.bininfo.presentation.BinListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleBin = module {

    single {
        Room.databaseBuilder(androidContext(), BinDB::class.java,
            "BinDB").build()
    }

    single { get<BinDB>().binDao }

    single<BinDataSource> {
        BinDataSourceImpl(
            get()
        )
    }

    single<BinApiDataSource> {
        BinApiDataSourceImpl(
            get()
        )
    }

    single<BinCall> {
        BinRepository(
            get(),get()
        )
    }

    single { BinUseCase(get()) }

    viewModel { BinInfoViewModel(get()) }
    viewModel { BinListViewModel(get()) }
}