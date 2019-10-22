package com.zhaojy.funny.data.livedata

import androidx.lifecycle.LiveData

/**
 *@author: zhaojy
 *@data:On 2019/10/20.
 */
class HistoryLiveData private constructor() : LiveData<String>() {

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()

    }

    public override fun postValue(value: String) {
        super.postValue(value)
    }

    public override fun setValue(value: String) {
        super.setValue(value)
    }

    public override fun getValue(): String? {
        return super.getValue()
    }

    companion object {
        private const val TAG = "HistoryLiveData"
        private var instance: HistoryLiveData? = null
            get() {
                if (field == null) {
                    field = HistoryLiveData()
                }
                return field
            }

        @Synchronized
        fun get(): HistoryLiveData {
            return instance!!
        }
    }

}