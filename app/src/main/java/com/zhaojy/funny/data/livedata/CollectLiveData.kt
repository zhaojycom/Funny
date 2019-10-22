package com.zhaojy.funny.data.livedata

import androidx.lifecycle.LiveData
import com.zhaojy.funny.data.bean.Collect

/**
 *@author: zhaojy
 *@data:On 2019/10/20.
 */
class CollectLiveData private constructor() : LiveData<Collect>() {

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()

    }

    public override fun postValue(value: Collect) {
        super.postValue(value)
    }

    public override fun setValue(value: Collect) {
        super.setValue(value)
    }

    public override fun getValue(): Collect? {
        return super.getValue()
    }

    companion object {
        private const val TAG = "CollectLiveData"
        private var instance: CollectLiveData? = null
            get() {
                if (field == null) {
                    field = CollectLiveData()
                }
                return field
            }

        @Synchronized
        fun get(): CollectLiveData {
            return instance!!
        }
    }

}