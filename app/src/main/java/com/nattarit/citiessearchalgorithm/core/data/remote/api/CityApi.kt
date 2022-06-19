package com.nattarit.citiessearchalgorithm.core.data.remote.api

import com.nattarit.citiessearchalgorithm.core.data.model.CityModel
import retrofit2.Call
import retrofit2.http.GET

interface CityApi {
    @GET("cities.json")
    fun getCityList(): Call<List<CityModel>>
}