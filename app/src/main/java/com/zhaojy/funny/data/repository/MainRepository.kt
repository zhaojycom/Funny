package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * @author: zhaojy
 * @data:On 2019/10/2.
 */
class MainRepository private constructor(private val network: FunnyNetwork) {

    suspend fun getBannerImgList() = withContext(Dispatchers.IO) {
        network.getBannerImgList()
    }

    suspend fun getArticleList(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.getArticleList(requestBody)
    }

    companion object {
        private var instance: MainRepository? = null

        fun getInstance(network: FunnyNetwork): MainRepository {
            if (instance == null) {
                synchronized(MainRepository::class.java) {
                    if (instance == null) {
                        instance =
                            MainRepository(network)
                    }
                }
            }
            return instance!!
        }

    }
}

