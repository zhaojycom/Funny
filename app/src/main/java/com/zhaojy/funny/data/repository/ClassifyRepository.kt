package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * @author: zhaojy
 * @data:On 2019/9/22.
 */
class ClassifyRepository private constructor(private val network: FunnyNetwork) {

    suspend fun getPlantClassifyList() = withContext(Dispatchers.IO) {
        network.getPlantClassifyList()
    }

    suspend fun getClassifyPlantList(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.getClassifyPlantList(requestBody)
    }

    fun cancelClassifyPlantList() {
        network.cancelClassifyPlantList()
    }

    companion object {
        private var instance: ClassifyRepository? = null

        fun getInstance(network: FunnyNetwork): ClassifyRepository {
            if (instance == null) {
                synchronized(ClassifyRepository::class.java) {
                    if (instance == null) {
                        instance =
                            ClassifyRepository(network)
                    }
                }
            }
            return instance!!
        }
    }
}
