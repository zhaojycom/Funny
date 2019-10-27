package com.zhaojy.funny.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaojy.funny.data.repository.MainRepository
import kotlinx.coroutines.launch

/**
 *@author: zhaojy
 *@data:On 2019/10/2.
 */
class MainModel(private val repository: MainRepository) : ViewModel() {
    var mBannerChanged = MutableLiveData(0)
    val mBannerImgList = ArrayList<String>()

    fun getBannerList() {
        viewModelScope.launch {
            try {
                val tempList = repository.getBannerImgList()
                for (item in tempList) {
                    mBannerImgList.add(item)
                }
                mBannerChanged.value = mBannerChanged.value?.plus(1)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    companion object {

    }

}