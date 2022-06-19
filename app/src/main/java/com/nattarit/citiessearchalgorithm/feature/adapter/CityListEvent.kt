package com.nattarit.citiessearchalgorithm.feature.adapter

import com.nattarit.citiessearchalgorithm.core.domain.entity.City

sealed class CityListEvent {
    data class OnClick(var position: Int, var city: City) : CityListEvent()
}