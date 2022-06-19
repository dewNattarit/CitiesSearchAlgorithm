package com.nattarit.citiessearchalgorithm.core.di

import com.nattarit.citiessearchalgorithm.feature.CityListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CityListViewModel() }
}