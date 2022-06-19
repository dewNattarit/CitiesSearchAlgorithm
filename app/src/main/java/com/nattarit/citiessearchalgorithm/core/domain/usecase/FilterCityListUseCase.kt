package com.nattarit.citiessearchalgorithm.core.domain.usecase

import com.nattarit.citiessearchalgorithm.core.domain.adapter.CityRemoteRepository
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.exception.Failure
import com.nattarit.citiessearchalgorithm.core.fuctionnal.Either
import com.nattarit.citiessearchalgorithm.core.interactor.UseCase

class FilterCityListUseCase constructor(private val cityRemoteRepository: CityRemoteRepository) :
    UseCase< List<City>, FilterCityListUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, List<City>> {
        return cityRemoteRepository.filterCities(params.keyWord,params.cities)
    }
    data class Params(val keyWord:String,val cities:List<City>)
}