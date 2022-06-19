package com.nattarit.citiessearchalgorithm.core.domain.adapter

import com.nattarit.citiessearchalgorithm.core.RequestHandler
import com.nattarit.citiessearchalgorithm.core.data.remote.CityService
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.exception.Failure
import com.nattarit.citiessearchalgorithm.core.fuctionnal.Either

interface CityRemoteRepository {
    fun getCityList(): Either<Failure, List<City>>

    class CityRemoteDataSource constructor(
        private val requestHandler: RequestHandler,
        private val cityService: CityService
    ) : CityRemoteRepository {
        override fun getCityList(): Either<Failure, List<City>> {
            return requestHandler.call(
                cityService.getCityList(), { cityModelList ->
                    cityModelList.map { it.toCity() }
                },ArrayList()
            )

        }


    }
}