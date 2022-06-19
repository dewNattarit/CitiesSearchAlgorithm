package com.nattarit.citiessearchalgorithm.core

import com.google.gson.JsonSyntaxException
import com.nattarit.citiessearchalgorithm.core.exception.Failure
import com.nattarit.citiessearchalgorithm.core.exception.NoConnectivityException
import com.nattarit.citiessearchalgorithm.core.fuctionnal.Either
import retrofit2.Call
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException


/**
 * Class for handling request/response/exceptions.
 * Every http exception should implement in try catch with specific failure type
 */
class RequestHandler{
    /**
     * Send request to server
     * call = message of service
     * transform = response data when server return
     * default = default value in case of no data return from server
     */
    fun <T : Any, R> call(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> {
                    try {
                        Either.Right(transform((response.body() ?: default)))
                    } catch (ex: Exception) {
                        Either.Left(Failure.InvalidResponse)
                    }
                }
                false -> {
                    when (response.code()) {
                        400 -> {
                            Either.Left(Failure.InvalidResponse)
                        }
                        401 -> {
                            Either.Left(Failure.UnAuthorizationError.apply {
                                this.httpCode = response.code()
                            })
                        }
                        403 -> {
                            Either.Left(Failure.InvalidAccessRightError.apply {
                                this.httpCode = response.code()
                            })
                        }
                        404 -> {
                            Either.Left(Failure.UrlNotFoundError.apply {
                                this.httpCode = response.code()
                            })
                        }
                        500, 502, 503 -> {
                            Either.Left(Failure.ServerError.apply {
                                this.httpCode = response.code()
                            })
                        }
                        else ->{
                            Either.Left(Failure.UnknownError.apply {
                                this.httpCode = response.code()
                            })
                        }
                    }
                }
            }
        } catch (ex: NoConnectivityException) {
            ex.printStackTrace()
            Either.Left(Failure.NetworkConnection)
        } catch (ex: SocketTimeoutException) {
            ex.printStackTrace()

            Either.Left(Failure.SocketTimeoutError)

        } catch (ex: UnknownHostException) {
            ex.printStackTrace()
            Either.Left(Failure.UnknownHostError)
        } catch (ex: SSLHandshakeException) {
            ex.printStackTrace()
            Either.Left(Failure.SSLHandshakeError)
        } catch (ex: JsonSyntaxException) {
            ex.printStackTrace()

            Either.Left(Failure.JsonSyntaxError)
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
            Either.Left(Failure.NullPointerError)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Either.Left(Failure.UnExpectedError)
        }
    }
}