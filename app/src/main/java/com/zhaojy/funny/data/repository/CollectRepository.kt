package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/6.
 */
class CollectRepository private constructor(private val network: FunnyNetwork) {

    suspend fun getCollectList(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.getCollectList(requestBody)
    }

    companion object {
        private var instance: CollectRepository? = null

        fun getInstance(network: FunnyNetwork): CollectRepository {
            if (instance == null) {
                synchronized(CollectRepository::class.java) {
                    if (instance == null) {
                        instance =
                            CollectRepository(network)
                    }
                }
            }
            return instance!!
        }

    }
}