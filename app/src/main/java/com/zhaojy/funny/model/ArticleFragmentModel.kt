package com.zhaojy.funny.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaojy.funny.data.bean.Article
import com.zhaojy.funny.data.repository.ArticleFragmentRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/27.
 */
class ArticleFragmentModel(private val repository: ArticleFragmentRepository) : ViewModel() {
    var mDataChanged = MutableLiveData(0)
    val mArticleList = ArrayList<Article>()

    fun getArticleList(requestBody: RequestBody) {
        viewModelScope.launch {
            try {
                val tempList = repository.getArticleList(requestBody)
                for (item in tempList) {
                    mArticleList.add(item)
                }
                mDataChanged.value = mDataChanged.value?.plus(1)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    companion object {
        const val LIMIT = 3
    }
}