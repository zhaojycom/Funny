package com.zhaojy.funny.data.network

import com.zhaojy.funny.data.network.api.FunnyService
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *@author: zhaojy
 *@data:On 2019/9/21.
 */
class FunnyNetwork {
    private val funnyService = ServiceCreator.create(FunnyService::class.java)

    suspend fun getPlantClassifyList() = funnyService.getPlantClassifyList().await()

    suspend fun getClassifyPlantList(requestBody: RequestBody) =
        funnyService.getClassifyPlantList(requestBody).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }

    companion object {
        private var network: FunnyNetwork? = null

        fun getInstance(): FunnyNetwork {
            if (network == null) {
                synchronized(FunnyNetwork::class.java) {
                    if (network == null) {
                        network = FunnyNetwork()
                    }
                }
            }
            return network!!
        }

    }
}
