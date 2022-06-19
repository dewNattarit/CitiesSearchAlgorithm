package com.nattarit.citiessearchalgorithm.core.domain.adapter

import com.nattarit.citiessearchalgorithm.core.platform.RequestHandler
import com.nattarit.citiessearchalgorithm.core.data.remote.CityService
import com.nattarit.citiessearchalgorithm.core.data.remote.model.CityModel
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.exception.Failure
import com.nattarit.citiessearchalgorithm.core.fuctionnal.Either
import java.lang.Exception

interface CityRemoteRepository {
    fun getCityList(): Either<Failure, List<City>>
    fun filterCities(keyWord: String, cities: List<City>): Either<Failure, List<City>>
    class CityRemoteDataSource constructor(
        private val requestHandler: RequestHandler,
        private val cityService: CityService,
    ) : CityRemoteRepository {
        private val TAG = javaClass.simpleName
        override fun getCityList(): Either<Failure, List<City>> {

            return requestHandler.call(
                cityService.getCityList(), { cityModelList ->
                    cityModelList.map { it.toCity() }

                }, ArrayList<CityModel>()
            )

        }

        override fun filterCities(
            keyWord: String,
            cities: List<City>
        ): Either<Failure, List<City>> {
            return try {
                cities.filter {
                    it.name?.contains(keyWord, true) == true ||
                            it.country?.contains(keyWord, true) == true
                }.let {
                    Either.Right<List<City>>(it)
                }
            } catch (e: Exception) {
                Either.Left<Failure>(Failure.UnknownError)
            }

        }
    }
}