package com.nattarit.citiessearchalgorithm.feature

import android.os.Bundle
import android.util.Log
import com.nattarit.citiessearchalgorithm.R
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import com.nattarit.citiessearchalgorithm.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity  : BaseActivity() {
    private val TAG = javaClass.simpleName
    private val CITY = "city"
    private var city:City? = null
    override fun layout(): Int  = R.layout.activity_city

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initInstance(savedInstanceState: Bundle?) {
        super.initInstance(savedInstanceState)
        city = getCity()
    }
    private fun getCity():City?{
        return   intent.extras!!.getParcelable<City>(CITY)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        replaceFragment(savedInstanceState)
        if (city != null){
            val title = "${city?.name}, ${city?.country}"
            tv_title.text = title
        }
        imb_back.setOnClickListener {
            finish()
        }
    }

    private fun replaceFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            Log.i(TAG, "replaceFragment")
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fl_container,
                    CityFragment.newInstance(city)
                ).commit()
        }
    }



}