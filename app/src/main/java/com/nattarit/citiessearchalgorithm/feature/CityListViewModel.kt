package com.nattarit.citiessearchalgorithm.feature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nattarit.citiessearchalgorithm.core.BaseViewModel
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.domain.usecase.GetCityListUseCase

class CityListViewModel constructor(private val getCityListUseCase: GetCityListUseCase):BaseViewModel() {
    private val TAG = javaClass.simpleName
    private var _cities: MutableLiveData<List<City>> = MutableLiveData()
    val cities: LiveData<List<City>> = _cities

    fun initData(){
        getCityList()
        //getMockCities()
    }
     private fun getCityList(){
         getCityListUseCase(GetCityListUseCase.Params(true)){
            it.fold(::handleFailure, ::handleGetCityListUseCase)
         }
      }
      private fun handleGetCityListUseCase(cities: List<City>){
          Log.i(TAG, "handleGetCityListUseCase: ${cities.size}")
          _cities.value = cities
      }



    private fun getMockCities(){
        val cityList = arrayListOf<City>()
        cityList.clear()
        val bkk1 = City()
        bkk1.id = 1
        bkk1.name = "Bangkok"
        bkk1.country = "BG"
        val bkk2 = City()
        bkk2.id = 2
        bkk2.name = "Bangkok"
        bkk2.country = "CD"
        val bkk3 = City()
        bkk3.id = 3
        bkk3.name = "Bangkok"
        bkk3.country = "SA"
        val bkk4 = City()
        bkk4.id = 4
        bkk4.name = "Bangkok"
        bkk4.country = "GD"
        cityList.add(bkk1)
        cityList.add(bkk2)
        cityList.add(bkk3)
        cityList.add(bkk4)
        cityList.add(bkk1)
        cityList.add(bkk2)
        cityList.add(bkk3)
        cityList.add(bkk1)
        cityList.add(bkk2)
        _cities.value = cityList


    }
}