package com.nattarit.citiessearchalgorithm

import com.nattarit.citiessearchalgorithm.core.AndroidTest
import com.nattarit.citiessearchalgorithm.core.data.remote.CityService
import com.nattarit.citiessearchalgorithm.core.domain.adapter.CityRemoteRepository
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.platform.RequestHandler
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class CityRemoteRepositoryTest : AndroidTest(){
    private lateinit var cityRemoteDataSource: CityRemoteRepository.CityRemoteDataSource
    @MockK
    private lateinit var requestHandler: RequestHandler
    @MockK
    private lateinit var cityService: CityService
    private lateinit var cities:List<City>
    @Before
    fun setUp() {
        cityRemoteDataSource = CityRemoteRepository.CityRemoteDataSource(
            requestHandler,
            cityService
        )
        cities = getCityList()

    }
    @Test
    fun `get 2 cities in list when key word is ua`(){
       val cities =  cityRemoteDataSource.handleFilterCities("ua",cities)
        cities.size shouldBeEqualTo 2
    }
    @Test
    fun `get 1 city in list when key word is dovo`(){
        val cities =  cityRemoteDataSource.handleFilterCities("dovo",cities)
        cities.size shouldBeEqualTo 1
    }

    @Test
    fun `get 1 city in list when key word is Hurzuf`(){
        val cities =  cityRemoteDataSource.handleFilterCities("Hurzuf",cities)
        cities.size shouldBeEqualTo 1
    }
    @Test
    fun `get 1 city in list when key word is vinogradovo`(){
        val cities =  cityRemoteDataSource.handleFilterCities("vinogradovo",cities)
        cities.size shouldBeEqualTo 1
    }

    @Test
    fun `get 0 city in list when key word is hello`(){
        val cities =  cityRemoteDataSource.handleFilterCities("hello",cities)
        cities.size shouldBeEqualTo 0
    }
    @Test
    fun `get 0 city in list when city list is empty`(){
        val cities =  cityRemoteDataSource.handleFilterCities("vinogradovo",ArrayList())
        cities.size shouldBeEqualTo 0
    }





    private fun getCityList(): List<City> {
        val cityList = arrayListOf<City>()
        val hurzuf = City()
        hurzuf.name = "Hurzuf"
        hurzuf.country = "UA"

        val holubynka = City()
        holubynka.name = "Holubynka"
        holubynka.country = "UA"

        val vinogradovo = City()
        vinogradovo.name = "Vinogradovo"
        vinogradovo.country = "RU"


        cityList.add(hurzuf)
        cityList.add(holubynka)
        cityList.add(vinogradovo)
        return cityList
    }

}