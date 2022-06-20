package com.nattarit.citiessearchalgorithm.feature

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.nattarit.citiessearchalgorithm.R
import com.nattarit.citiessearchalgorithm.core.exception.Failure
import com.nattarit.citiessearchalgorithm.core.platform.BaseFragment
import com.nattarit.citiessearchalgorithm.feature.adapter.CityListAdapter
import com.nattarit.citiessearchalgorithm.feature.adapter.CityListEvent
import kotlinx.android.synthetic.main.fragment_city_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListFragment : BaseFragment() {
    private val TAG = javaClass.simpleName
    private val viewModel: CityListViewModel by viewModel()
    private val cityListAdapter = CityListAdapter()
    override fun layoutId(): Int = R.layout.fragment_city_list

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        initCityListAdapter()
        initSearchView()
    }
    private fun initSearchView(){

        edt_search.doAfterTextChanged {
            viewModel.filterCityList(it.toString())
        }
        imb_clear.setOnClickListener {
            viewModel.clearSearch()
        }
    }

    private fun initCityListAdapter() {
        rcv_city_list.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = cityListAdapter
        }
        cityListAdapter.event.observe(viewLifecycleOwner){
            handleEvent(it)
        }
    }
    private fun handleEvent(event:CityListEvent){
        if (event is CityListEvent.OnClick ){
            Log.d(TAG, "handleEvent: ${event.city}")
            val intent = Intent(requireContext(), CityActivity::class.java)
            intent.putExtra("city",event.city)
            startActivity(intent)
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.setCityList.observe(viewLifecycleOwner){
            cityListAdapter.setData(items = it)
        }
        viewModel.isShowLoadingView.observe(viewLifecycleOwner){
            showLoadingView(isShow = it)
        }
        viewModel.failure.observe(viewLifecycleOwner){
            handleFailure(failure = it)
        }
        viewModel.isShowEmptyView.observe(viewLifecycleOwner){
            showEmptyView(isShow = it)
        }
        viewModel.clearKeyWord.observe(viewLifecycleOwner){
            clearKeyWord()
        }
        viewModel.isShowClearButton.observe(viewLifecycleOwner){
            showClearButtonView(isShow = it)
        }
    }
    private fun clearKeyWord(){
        edt_search.setText("")
    }

    private fun showClearButtonView(isShow: Boolean){
        if (isShow){
            showView(imb_clear)
        }else{
            hideView(imb_clear)
        }
    }
    private fun showEmptyView(isShow: Boolean){
        if (isShow){
            showView(tv_not_found)
        }else{
            hideView(tv_not_found)
        }
    }
    private fun showLoadingView(isShow:Boolean){
        if (isShow){
            hideView(rcv_city_list)
            showView(rel_progress)
        }else{
            showView(rcv_city_list)
            hideView(rel_progress)
        }
    }


    override fun onViewModelObserved(savedInstanceState: Bundle?) {
        super.onViewModelObserved(savedInstanceState)
        if (savedInstanceState == null){
            viewModel.initData()
        }
    }

    override fun handleFailure(failure: Failure) {
        super.handleFailure(failure)
        Log.e(TAG, "handleFailure: ERROR : $failure")
    }

}