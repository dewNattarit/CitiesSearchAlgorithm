package com.nattarit.citiessearchalgorithm.feature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nattarit.citiessearchalgorithm.core.platform.BaseViewModel
import com.nattarit.citiessearchalgorithm.core.platform.SingleLiveEvent
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.domain.usecase.FilterCityListUseCase
import com.nattarit.citiessearchalgorithm.core.domain.usecase.GetCityListUseCase
import com.nattarit.citiessearchalgorithm.core.interactor.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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
    private var _clearKeyWord: SingleLiveEvent<Void> = SingleLiveEvent()
    val clearKeyWord: LiveData<Void> = _clearKeyWord
    private var _isShowClearButton: MutableLiveData<Boolean> = MutableLiveData()
    val isShowClearButton: LiveData<Boolean> = _isShowClearButton

    private var cityList:List<City>? = null
    private var isGettingCityList = false


    fun initData() {
        getCityList()
    }

    private fun getCityList() {
        isGettingCityList = true
        showLoadingView(true)
        getCityListUseCase(UseCase.None()) {
            it.fold(::handleFailure, ::handleGetCityListUseCase)
        }
    }

    private fun handleGetCityListUseCase(cities: List<City>) {
        Log.i(TAG, "handleGetCityListUseCase: ${cities.size}")
        isGettingCityList = false
        this.cityList = cities
        setCityList(cities)
        showLoadingView(false)
        showEmptyState(cities.isNullOrEmpty())
    }
     fun filterCityList(keyWord:String){
         showLoadingView(true)
         if (keyWord.isBlank()){
             setCityList(cityList)
             showClearButtonView(false)
             showLoadingView(false)
             return
         }
         if (cityList.isNullOrEmpty()){
             showEmptyState(true)
             showLoadingView(false)
             showClearButtonView(true)
             return
         }

         showClearButtonView(true)
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

    fun clearSearch(){
        _clearKeyWord.call()
    }

    private fun setCityList(cities: List<City>?){
        _setCityList.value = cities?:ArrayList()
    }
    private fun showLoadingView(isShow:Boolean){
        if (isGettingCityList && !isShow){
            return
        }
        _isShowLoadingView.value = isShow
    }
     fun showEmptyState(isShow:Boolean){
        Log.i(TAG, "showEmptyState: $isShow")
        if (isGettingCityList){
            return
        }
         _isShowEmptyState.value = isShow
    }
    private fun showClearButtonView(isShow: Boolean){

        _isShowClearButton.value = isShow
    }


    fun setCityListForTest(cities: List<City>){
        isGettingCityList = false
        this.cityList = cities
    }





}