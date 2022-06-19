package com.nattarit.citiessearchalgorithm.feature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nattarit.citiessearchalgorithm.core.BaseViewModel
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.domain.usecase.FilterCityListUseCase
import com.nattarit.citiessearchalgorithm.core.domain.usecase.GetCityListUseCase

class CityListViewModel constructor(
    private val getCityListUseCase: GetCityListUseCase,
    private val filterCityListUseCase: FilterCityListUseCase
    ) : BaseViewModel() {
    private val TAG = javaClass.simpleName

    private var _setCityList: MutableLiveData<List<City>> = MutableLiveData()
    val setCityList: LiveData<List<City>> = _setCityList
    private var _isShowLoadingView: MutableLiveData<Boolean> = MutableLiveData()
    val isShowLoadingView: LiveData<Boolean> = _isShowLoadingView
    private var _isShowEmptyState: MutableLiveData<Boolean> = MutableLiveData()
    val isShowEmptyView: LiveData<Boolean> = _isShowEmptyState

    private var cityList:List<City>? = null


    fun initData() {
        getCityList()
    }

    private fun getCityList() {
        showLoadingView(true)
        getCityListUseCase(GetCityListUseCase.Params(true)) {
            it.fold(::handleFailure, ::handleGetCityListUseCase)
        }
    }

    private fun handleGetCityListUseCase(cities: List<City>) {
        Log.i(TAG, "handleGetCityListUseCase: ${cities.size}")
        this.cityList = cities
        setCityList(cities)
        showLoadingView(false)
        showEmptyState(cities.isNullOrEmpty())
    }
     fun filterCityList(keyWord:String){
         showLoadingView(true)
         if (cityList.isNullOrEmpty()){
             showEmptyState(true)
             showLoadingView(false)
             return
         }
         if (keyWord.isBlank()){
             setCityList(cityList)
             showLoadingView(false)
             return
         }

         filterCityListUseCase(FilterCityListUseCase.Params(keyWord,cityList?:ArrayList())){
            it.fold(::handleFailure, ::handleFilterCityListUseCase)
         }
      }
      private fun handleFilterCityListUseCase(cities: List<City>){
          Log.i(TAG, "handleFilterCityListUseCase: ${cities.size}")
          setCityList(cities)
          showLoadingView(false)
          showEmptyState(cities.isNullOrEmpty())


      }



    private fun setCityList(cities: List<City>?){
        _setCityList.value = cities?:ArrayList()
    }
    private fun showLoadingView(isShow:Boolean){
        _isShowLoadingView.value = isShow
    }
    private fun showEmptyState(isShow:Boolean){
        _isShowEmptyState.value = isShow
    }



}