package com.zhaojy.funny.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaojy.funny.bean.History
import com.zhaojy.funny.data.repository.HistoryRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/7.
 */
class HistoryModel (private val repository: HistoryRepository) : ViewModel() {
    val mHistoryList = ArrayList<History>()
    val mHistoryListChanged = MutableLiveData(0)

    fun getCollectList(requestBody: RequestBody) {
        viewModelScope.launch {
            try {
                val tempList = repository.getHistoryList(requestBody)
                for (item in tempList) {
                    mHistoryList.add(item)
                }
                mHistoryListChanged.value = mHistoryListChanged.value?.plus(1)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}