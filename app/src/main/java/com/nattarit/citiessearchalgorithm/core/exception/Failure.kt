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
package com.nattarit.citiessearchalgorithm.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {

    open var httpCode: Int = 0
    object NetworkConnection : Failure()
    object SocketTimeoutError : Failure()
    object UnknownHostError : Failure()
    object SSLHandshakeError : Failure()
    object UnAuthorizationError : Failure()
    object InvalidAccessRightError : Failure()
    object UnknownError : Failure()
    object ServerError : Failure()
    object JsonSyntaxError : Failure()
    object NullPointerError : Failure()
    object UnExpectedError : Failure()
    object UrlNotFoundError : Failure()
    object ApiError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
    class MappingError(
        val e: Throwable) : FeatureFailure()




}
