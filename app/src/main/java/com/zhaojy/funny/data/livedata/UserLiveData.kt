package com.zhaojy.funny.data.livedata

import androidx.lifecycle.LiveData
import com.zhaojy.funny.data.bean.User

/**
 *@author: zhaojy
 *@data:On 2019/10/4.
 */
class UserLiveData private constructor() : LiveData<User>() {

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()

    }

    companion object {
        private const val TAG = "UserLiveData"
        private var instance: UserLiveData? = null
            get() {
                if (field == null) {
                    field = UserLiveData()
                }
                return field
            }

        @Synchronized
        fun get(): UserLiveData {
            return instance!!
        }
    }

}
