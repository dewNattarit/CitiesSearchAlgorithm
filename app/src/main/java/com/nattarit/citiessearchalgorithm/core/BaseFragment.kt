/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nattarit.citiessearchalgorithm.core

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nattarit.citiessearchalgorithm.core.exception.Failure


/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragment : Fragment() {


    abstract fun layoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        observeViewModel()
        onViewModelObserved(savedInstanceState)
    }

    open fun initView(savedInstanceState: Bundle?) {

    }

    open fun observeViewModel() {

    }

    open fun onViewModelObserved(savedInstanceState: Bundle?) {

    }

    open fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.NetworkConnection -> {
                Log.e("handleFailure", "ERROR: (801) NetworkConnection")
            }
            is Failure.SocketTimeoutError -> {
                Log.e("handleFailure", "ERROR: (802) SocketTimeoutError")
            }
            is Failure.UnknownHostError -> {
                Log.e("handleFailure", "ERROR: (803) UnknownHostError")
            }
            is Failure.SSLHandshakeError -> {
                Log.e("handleFailure", "ERROR: (804) SSLHandshakeError")
            }
            is Failure.JsonSyntaxError -> {
                Log.e("handleFailure", "ERROR: (805) JsonSyntaxError")
            }
            is Failure.NullPointerError -> {
                Log.e("handleFailure", "ERROR: (806) NullPointerError")
            }
            is Failure.UnExpectedError -> {
                Log.e("handleFailure", "ERROR: ${failure.httpCode} UnExpectedError")
            }
            is Failure.UnAuthorizationError -> {
                Log.e("handleFailure", "ERROR: 401 UnAuthorizationError")
            }
            is Failure.InvalidAccessRightError -> {
                Log.e("handleFailure", "ERROR: 403 InvalidAccessRightError")
            }

            is Failure.UrlNotFoundError -> {
                Log.e("handleFailure", "ERROR: 404 UrlNotFoundError")
            }
            is Failure.ServerError -> {
                Log.e("handleFailure", "ERROR: 500 ServerError")
            }

            is Failure.ApiError -> {
                Log.e("handleFailure", "ERROR: 400 ApiError")

            }

            is Failure.UnknownError -> {
                Log.e("handleFailure", "ERROR: ${failure.httpCode} UnknownError")
            }
            else -> {
                Log.e("handleFailure", "ERROR: ${failure.httpCode} UnknownError")
            }
        }
    }



    fun showView(view: View) {
        view.visibility = View.VISIBLE
    }

    fun hideView(view: View) {
        view.visibility = View.GONE
    }





}
