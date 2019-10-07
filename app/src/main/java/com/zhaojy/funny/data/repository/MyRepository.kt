package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/4.
 */
class MyRepository private constructor(private val network: FunnyNetwork) {
    suspend fun getCollectSum(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.getCollectSum(requestBody)
    }

    suspend fun getHistorySum(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.getHistorySum(requestBody)
    }

    companion object {
        private var instance: MyRepository? = null

        fun getInstance(network: FunnyNetwork): MyRepository {
            if (instance == null) {
                synchronized(MyRepository::class.java) {
                    if (instance == null) {
                        instance =
                            MyRepository(network)
                    }
                }
            }
            return instance!!
        }

    }

}