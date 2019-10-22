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

    suspend fun getBannerImgList() =
        funnyService.getBannerImgList().await()

    suspend fun getArticleList(requestBody: RequestBody) =
        funnyService.getArticleList(requestBody).await()

    suspend fun login(requestBody: RequestBody) =
        funnyService.login(requestBody).await()

    suspend fun getCollectList(requestBody: RequestBody) =
        funnyService.readCollect(requestBody).await()

    suspend fun getHistoryList(requestBody: RequestBody) =
        funnyService.readHistory(requestBody).await()

    suspend fun getCollectSum(requestBody: RequestBody) =
        funnyService.getCollectSum(requestBody).await()

    suspend fun getHistorySum(requestBody: RequestBody) =
        funnyService.getHistorySum(requestBody).await()

    suspend fun getCollectInfo(requestBody: RequestBody) =
        funnyService.getCollectInfo(requestBody).await()

    suspend fun collect(requestBody: RequestBody) =
        funnyService.collect(requestBody).await()

    suspend fun cancelCollect(requestBody: RequestBody) =
        funnyService.cancelCollect(requestBody).await()

    suspend fun recordHistory(requestBody: RequestBody) =
        funnyService.recordHistory(requestBody).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    continuation.resume(body as T)
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
