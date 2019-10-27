package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/27.
 */
class ArticleFragmentRepository private constructor(private val network: FunnyNetwork) {

    suspend fun getArticleList(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.getArticleList(requestBody)
    }

    companion object {
        private var instance: ArticleFragmentRepository? = null

        fun getInstance(network: FunnyNetwork): ArticleFragmentRepository {
            if (instance == null) {
                synchronized(ArticleFragmentRepository::class.java) {
                    if (instance == null) {
                        instance =
                            ArticleFragmentRepository(network)
                    }
                }
            }
            return instance!!
        }
    }
}