package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/7.
 */
class HistoryRepository private constructor(private val network: FunnyNetwork) {

    suspend fun getHistoryList(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.getHistoryList(requestBody)
    }

    companion object {
        private var instance: HistoryRepository? = null

        fun getInstance(network: FunnyNetwork): HistoryRepository {
            if (instance == null) {
                synchronized(HistoryRepository::class.java) {
                    if (instance == null) {
                        instance =
                            HistoryRepository(network)
                    }
                }
            }
            return instance!!
        }

    }
}