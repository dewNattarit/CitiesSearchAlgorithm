package com.nattarit.citiessearchalgorithm.core.data.remote

import com.nattarit.citiessearchalgorithm.core.platform.RetrofitClient
import com.nattarit.citiessearchalgorithm.core.data.remote.api.CityApi
import com.nattarit.citiessearchalgorithm.core.data.remote.model.CityModel
import retrofit2.Call

class CityService constructor(retrofit: RetrofitClient) : CityApi {
    private val siriusAndroidEndpoint by lazy {
        retrofit.build("https://raw.githubusercontent.com/SiriusAndroid/android-assignment/master/")
            .create(CityApi::class.java)
    }
    override fun getCityList(): Call<List<CityModel>> {
        return siriusAndroidEndpoint.getCityList()
    }
}