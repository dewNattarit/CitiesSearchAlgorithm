package com.nattarit.citiessearchalgorithm.core.di

import com.nattarit.citiessearchalgorithm.core.RequestHandler
import com.nattarit.citiessearchalgorithm.core.RetrofitClient
import com.nattarit.citiessearchalgorithm.core.data.remote.CityService
import com.nattarit.citiessearchalgorithm.core.domain.adapter.CityRemoteRepository
import com.nattarit.citiessearchalgorithm.core.domain.usecase.GetCityListUseCase
import com.nattarit.citiessearchalgorithm.feature.CityListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    /** ViewModel */
    viewModel { CityListViewModel(get()) }

    /** UseCase */
    single { GetCityListUseCase(get()) }

    /** Repository */
    single<CityRemoteRepository> { CityRemoteRepository.CityRemoteDataSource(get(), get()) }

    /** Service */
    single { CityService(get()) }

    /** Networking */
    single { RetrofitClient() }
    single { RequestHandler() }
}