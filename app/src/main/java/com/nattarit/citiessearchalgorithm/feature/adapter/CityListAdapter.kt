package com.nattarit.citiessearchalgorithm.feature.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.nattarit.citiessearchalgorithm.R
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.feature.adapter.CityListEvent

class CityListAdapter(val event: MutableLiveData<CityListEvent> = MutableLiveData()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = javaClass.simpleName
    private var data: ArrayList<City> = ArrayList()
    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(items: List<City>) {
        Log.d(TAG, "setData: ${items.size}")
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city_list, parent, false)
        return CityListViewHolder(view, event)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CityListViewHolder -> {
                holder.bind(data[position], position)
            }
        }
    }


}