package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/19.
 */
class PlantRepository private constructor(private val network: FunnyNetwork) {

    suspend fun getCollectInfo(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.getCollectInfo(requestBody)
    }

    suspend fun collect(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.collect(requestBody)
    }

    suspend fun cancelCollect(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.cancelCollect(requestBody)
    }

    suspend fun recordHistory(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.recordHistory(requestBody)
    }

    companion object {
        private var instance: PlantRepository? = null

        fun getInstance(network: FunnyNetwork): PlantRepository {
            if (instance == null) {
                synchronized(PlantRepository::class.java) {
                    if (instance == null) {
                        instance =
                            PlantRepository(network)
                    }
                }
            }
            return instance!!
        }
    }
}