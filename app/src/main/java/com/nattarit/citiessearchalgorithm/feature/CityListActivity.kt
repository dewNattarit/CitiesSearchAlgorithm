package com.nattarit.citiessearchalgorithm.feature

import android.os.Bundle
import android.util.Log
import com.nattarit.citiessearchalgorithm.R
import com.nattarit.citiessearchalgorithm.core.platform.BaseActivity

class CityListActivity : BaseActivity() {
    private val TAG = javaClass.simpleName
    override fun layout(): Int  = R.layout.activity_city_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(savedInstanceState)
    }

    private fun replaceFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            Log.i(TAG, "replaceFragment")
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fl_container,
                    CityListFragment()
                ).commit()
        }
    }

}