package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/5.
 */
class LoginRepository private constructor(private val network: FunnyNetwork) {
    suspend fun login(requestBody: RequestBody) = withContext(Dispatchers.IO) {
        network.login(requestBody)
    }

    companion object {
        private var instance: LoginRepository? = null

        fun getInstance(network: FunnyNetwork): LoginRepository {
            if (instance == null) {
                synchronized(LoginRepository::class.java) {
                    if (instance == null) {
                        instance =
                            LoginRepository(network)
                    }
                }
            }
            return instance!!
        }

    }
}