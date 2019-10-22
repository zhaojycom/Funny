package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/20.
 */
class ArticleRepository private constructor(private val network: FunnyNetwork) {

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
        private var instance: ArticleRepository? = null

        fun getInstance(network: FunnyNetwork): ArticleRepository {
            if (instance == null) {
                synchronized(ArticleRepository::class.java) {
                    if (instance == null) {
                        instance =
                            ArticleRepository(network)
                    }
                }
            }
            return instance!!
        }
    }
}