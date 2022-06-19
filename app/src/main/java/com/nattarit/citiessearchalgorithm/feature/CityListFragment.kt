package com.nattarit.citiessearchalgorithm.feature

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nattarit.citiessearchalgorithm.R
import com.nattarit.citiessearchalgorithm.core.BaseFragment
import com.nattarit.citiessearchalgorithm.feature.adapter.CityListAdapter
import kotlinx.android.synthetic.main.fragment_city_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListFragment : BaseFragment() {
    private val viewModel: CityListViewModel by viewModel()
    private val cityListAdapter = CityListAdapter()
    override fun layoutId(): Int = R.layout.fragment_city_list

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        initCityListAdapter()
    }

    private fun initCityListAdapter() {
        rcv_city_list.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = cityListAdapter
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.cities.observe(viewLifecycleOwner){
            cityListAdapter.setData(items = it)
        }
    }

    override fun onViewModelObserved(savedInstanceState: Bundle?) {
        super.onViewModelObserved(savedInstanceState)
        if (savedInstanceState == null){
            viewModel.initData()
        }
    }

}