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
import androidx.appcompat.app.AppCompatActivity
import com.nattarit.citiessearchalgorithm.core.exception.Failure


/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity(){

    abstract fun layout(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        initInstance(savedInstanceState)
        initView(savedInstanceState)
        observeViewModel()
    }

    open fun initInstance(savedInstanceState: Bundle?) {

    }

    open fun initView(savedInstanceState: Bundle?) {

    }

    open fun observeViewModel() {

    }

    open fun  handleFailure(failure: Failure) {
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



}
