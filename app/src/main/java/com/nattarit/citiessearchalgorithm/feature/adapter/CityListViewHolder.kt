package com.nattarit.citiessearchalgorithm.feature.adapter

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.nattarit.citiessearchalgorithm.core.platform.BaseViewHolder
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import kotlinx.android.synthetic.main.item_city_list.view.*

class CityListViewHolder (
    itemView: View,
    event: MutableLiveData<CityListEvent>,
    val data:List<City>
) :
    BaseViewHolder<City>(itemView) {
    private val TAG = javaClass.simpleName


    init {

        itemView.ctl_city.setOnClickListener {
            event.value  = CityListEvent.OnClick(adapterPosition,data[adapterPosition])
        }
    }

    override fun bind(item: City, position: Int) {
        val textCity = "${item.name} , ${item.country}"
        itemView.tv_cityName.text = textCity
    }
}