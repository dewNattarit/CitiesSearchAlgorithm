package com.nattarit.citiessearchalgorithm.feature.adapter

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.nattarit.citiessearchalgorithm.core.BaseViewHolder
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import kotlinx.android.synthetic.main.item_city_list.view.*

class CityListViewHolder (
    itemView: View,
    event: MutableLiveData<CityListEvent>,
) :
    BaseViewHolder<City>(itemView) {
    private val TAG = javaClass.simpleName



    override fun bind(item: City, position: Int) {
        val textCity = "${item.name} , ${item.country}"
        itemView.tv_cityName.text = textCity
    }
}