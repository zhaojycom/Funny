package com.zhaojy.funny.data.network

import com.zhaojy.funny.bean.Plant
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
    private val mFunnyService = ServiceCreator.create(FunnyService::class.java)
    private lateinit var mClassifyPlantCall: Call<List<Plant>>

    suspend fun getPlantClassifyList() = mFunnyService.getPlantClassifyList().await()

    suspend fun getClassifyPlantList(requestBody: RequestBody): List<Plant> {
        mClassifyPlantCall = mFunnyService.getClassifyPlantList(requestBody)
        return mClassifyPlantCall.await()
    }

    fun cancelClassifyPlantList() {
        mClassifyPlantCall?.cancel()
    }

    suspend fun getBannerImgList() =
        mFunnyService.getBannerImgList().await()

    suspend fun getArticleList(requestBody: RequestBody) =
        mFunnyService.getArticleList(requestBody).await()

    suspend fun login(requestBody: RequestBody) =
        mFunnyService.login(requestBody).await()

    suspend fun getCollectList(requestBody: RequestBody) =
        mFunnyService.readCollect(requestBody).await()

    suspend fun getHistoryList(requestBody: RequestBody) =
        mFunnyService.readHistory(requestBody).await()

    suspend fun getCollectSum(requestBody: RequestBody) =
        mFunnyService.getCollectSum(requestBody).await()

    suspend fun getHistorySum(requestBody: RequestBody) =
        mFunnyService.getHistorySum(requestBody).await()

    suspend fun getCollectInfo(requestBody: RequestBody) =
        mFunnyService.getCollectInfo(requestBody).await()

    suspend fun collect(requestBody: RequestBody) =
        mFunnyService.collect(requestBody).await()

    suspend fun cancelCollect(requestBody: RequestBody) =
        mFunnyService.cancelCollect(requestBody).await()

    suspend fun recordHistory(requestBody: RequestBody) =
        mFunnyService.recordHistory(requestBody).await()

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
