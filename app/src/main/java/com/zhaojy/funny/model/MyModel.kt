package com.zhaojy.funny.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaojy.funny.bean.Count
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.data.bean.User
import com.zhaojy.funny.data.livedata.UserLiveData
import com.zhaojy.funny.data.repository.LoginRepository
import com.zhaojy.funny.data.repository.MyRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/4.
 */
class MyModel(
    private val myRepository: MyRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {
    var mUserLiveData = UserLiveData.get()
    var mCollectCount: Count? = Count()
    var mHistoryCount: Count? = Count()
    var mCollectSumLiveData = MutableLiveData(0)
    var mHistorySumLiveData = MutableLiveData(0)

    fun login(phoneNumber: String) {
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), phoneNumber
        )
        viewModelScope.launch {
            try {
                val user: User = loginRepository.login(body)
                mUserLiveData.postValue(user)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun getCollectSum(phoneNumber: String) {
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), phoneNumber
        )
        viewModelScope.launch {
            try {
                mCollectCount = myRepository.getCollectSum(body)
                mCollectSumLiveData.value = mCollectSumLiveData.value?.plus(1)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun getHistorySum(phoneNumber: String) {
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), phoneNumber
        )
        viewModelScope.launch {
            try {
                mHistoryCount = myRepository.getHistorySum(body)
                mHistorySumLiveData.value = mHistorySumLiveData.value?.plus(1)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}