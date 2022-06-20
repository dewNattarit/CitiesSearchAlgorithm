package com.nattarit.citiessearchalgorithm.core.domain.usecase

import com.nattarit.citiessearchalgorithm.core.domain.adapter.CityRemoteRepository
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.exception.Failure
import com.nattarit.citiessearchalgorithm.core.fuctionnal.Either
import com.nattarit.citiessearchalgorithm.core.interactor.UseCase

class GetCityListUseCase constructor(private val cityRemoteRepository: CityRemoteRepository) :
    UseCase< List<City>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<City>> {
        return cityRemoteRepository.getCityList()
    }

}