package com.zhaojy.funny.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaojy.funny.data.bean.Article
import com.zhaojy.funny.data.repository.MainRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/2.
 */
class MainModel(private val repository: MainRepository) : ViewModel() {
    var mBannerChanged = MutableLiveData(0)
    var mArticleChanged = MutableLiveData(0)
    val mBannerImgList = ArrayList<String>()
    val mMainArticleList = ArrayList<Article>()

    fun getArticleList(requestBody: RequestBody) {
        viewModelScope.launch {
            try {
                val tempList = repository.getArticleList(requestBody)
                for (item in tempList) {
                    mMainArticleList.add(item)
                }
                mArticleChanged.value = mArticleChanged.value?.plus(1)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

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
        const val LIMIT = 2

    }

}