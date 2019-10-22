package com.zhaojy.funny.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.data.bean.Collect
import com.zhaojy.funny.data.livedata.CollectLiveData
import com.zhaojy.funny.data.livedata.HistoryLiveData
import com.zhaojy.funny.data.repository.PlantRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody


/**
 *@author: zhaojy
 *@data:On 2019/10/19.
 */
class PlantModel(private val repository: PlantRepository) : ViewModel() {
    var mCollect: Collect? = null
    var mDataChanged = MutableLiveData(0)
    private var mCollectLiveData = CollectLiveData.get()
    private var mHistoryLiveData = HistoryLiveData.get()

    fun getCollectInfo(requestBody: RequestBody) {
        viewModelScope.launch {
            try {
                mCollect = repository.getCollectInfo(requestBody)
                mDataChanged.value = mDataChanged.value?.plus(1)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun collect(requestBody: RequestBody) {
        viewModelScope.launch {
            try {
                mCollect = repository.collect(requestBody)
                mDataChanged.value = mDataChanged.value?.plus(1)
                mCollectLiveData.setValue(mCollect as Collect)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun cancelCollect(id: Int?) {
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), id.toString()
        )
        viewModelScope.launch {
            try {
                mCollect = repository.cancelCollect(body)
                mDataChanged.value = mDataChanged.value?.plus(1)
                mCollectLiveData.setValue(Collect())
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun recordHistory(requestBody: RequestBody) {
        viewModelScope.launch {
            try {
                val history: String = repository.recordHistory(requestBody)
                mHistoryLiveData.setValue(history)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}