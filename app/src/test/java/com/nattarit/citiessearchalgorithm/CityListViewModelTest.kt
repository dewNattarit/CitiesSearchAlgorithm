package com.nattarit.citiessearchalgorithm

import com.nattarit.citiessearchalgorithm.core.AndroidTest
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.domain.usecase.FilterCityListUseCase
import com.nattarit.citiessearchalgorithm.core.domain.usecase.GetCityListUseCase
import com.nattarit.citiessearchalgorithm.core.fuctionnal.Either
import com.nattarit.citiessearchalgorithm.core.getOrAwaitValue
import com.nattarit.citiessearchalgorithm.core.interactor.UseCase
import com.nattarit.citiessearchalgorithm.feature.CityListViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class CityListViewModelTest : AndroidTest() {
    private lateinit var cityListViewModel: CityListViewModel

    @MockK
    private lateinit var getCityListUseCase: GetCityListUseCase

    @MockK
    private lateinit var filterCityListUseCase: FilterCityListUseCase


    @Before
    fun setUp() {
        cityListViewModel = CityListViewModel(
            getCityListUseCase,
            filterCityListUseCase
        )
    }

    @Test
    fun `get city list`() {
        runBlocking { cityListViewModel.initData() }
        coEvery { getCityListUseCase.run(UseCase.None()) } returns (Either.Right(getCityList()))
    }

    @Test
    fun `hide loading view and clear button view when keyword is blank and get city list successful`() {
        cityListViewModel.setCityListForTest(ArrayList())
        cityListViewModel.filterCityList("")
        val isShowLoadingView = cityListViewModel.isShowLoadingView.getOrAwaitValue()
        val isShowClearButton = cityListViewModel.isShowClearButton.getOrAwaitValue()
        isShowLoadingView shouldBeEqualTo false
        isShowLoadingView shouldBeEqualTo false
    }

    @Test
    fun `hide loading view and clear button view when keyword is blank and call getting city`() {
        cityListViewModel.filterCityList("")
        val isShowLoadingView = cityListViewModel.isShowLoadingView.getOrAwaitValue()
        val isShowClearButton = cityListViewModel.isShowClearButton.getOrAwaitValue()
        isShowLoadingView shouldBeEqualTo false
        isShowClearButton shouldBeEqualTo false

    }

    @Test
    fun `hide loading view , show clear button view and show empty view when keyword is not blank and get city empty list `() {
        cityListViewModel.setCityListForTest(ArrayList())
        cityListViewModel.filterCityList("Hurzuf")
        val isShowLoadingView = cityListViewModel.isShowLoadingView.getOrAwaitValue()
        val isShowClearButton = cityListViewModel.isShowClearButton.getOrAwaitValue()
        val isShowEmptyView = cityListViewModel.isShowEmptyView.getOrAwaitValue()
        isShowClearButton shouldBeEqualTo true
        isShowLoadingView shouldBeEqualTo false
        isShowEmptyView shouldBeEqualTo true

    }

    @Test
    fun `clear key word`() {
        cityListViewModel.setCityListForTest(getCityList())
        cityListViewModel.clearSearch()
        cityListViewModel.clearKeyWord.getOrAwaitValue()
    }

    private fun getCityList(): List<City> {
        val cityList = arrayListOf<City>()
        val hurzuf = City()
        hurzuf.name = "Hurzuf"
        hurzuf.country = "UA"
        cityList.add(hurzuf)
        return cityList
    }


}