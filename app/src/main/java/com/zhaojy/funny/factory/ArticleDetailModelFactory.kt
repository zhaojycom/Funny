package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.ArticleDetailRepository
import com.zhaojy.funny.model.ArticleDetailModel

/**
 *@author: zhaojy
 *@data:On 2019/10/20.
 */
class ArticleDetailModelFactory (private val repository: ArticleDetailRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleDetailModel(repository) as T
    }
}