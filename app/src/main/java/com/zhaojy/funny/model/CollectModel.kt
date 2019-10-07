package com.zhaojy.funny.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaojy.funny.data.bean.Collect
import com.zhaojy.funny.data.repository.CollectRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/6.
 */
class CollectModel(private val repository: CollectRepository) : ViewModel() {
    val mCollectList = ArrayList<Collect>()
    val mCollectListChanged = MutableLiveData(0)

    fun getCollectList(requestBody: RequestBody) {
        viewModelScope.launch {
            try {
                val tempList = repository.getCollectList(requestBody)
                for (item in tempList) {
                    mCollectList.add(item)
                }
                mCollectListChanged.value = mCollectListChanged.value?.plus(1)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}