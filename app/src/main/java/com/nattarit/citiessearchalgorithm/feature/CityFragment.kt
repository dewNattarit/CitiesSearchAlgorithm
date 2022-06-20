package com.nattarit.citiessearchalgorithm.feature

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import com.nattarit.citiessearchalgorithm.R
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_city.*


class CityFragment : BaseFragment() {
    private val CITY = "city"

    companion object {
        @JvmStatic
        fun newInstance(city: City?) =
            CityFragment().apply {
                arguments = Bundle().apply {
                    this.putParcelable(CITY, city)
                }
            }
    }

    override fun layoutId(): Int = R.layout.fragment_city

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        val city = arguments?.getParcelable<City>(CITY)
        wv_map.webViewClient = WebViewClient()
        wv_map.settings.javaScriptEnabled = true
        wv_map.loadUrl("http://maps.google.com/maps?q=${city?.coordinate?.latitude},${city?.coordinate?.longitude}")
        //wv_map.loadUrl("https://www.google.com/")

    }
}