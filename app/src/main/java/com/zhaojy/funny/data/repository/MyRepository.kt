package com.zhaojy.funny.data.repository

import com.zhaojy.funny.data.network.FunnyNetwork

/**
 *@author: zhaojy
 *@data:On 2019/10/4.
 */
class MyRepository private constructor(private val network: FunnyNetwork) {
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