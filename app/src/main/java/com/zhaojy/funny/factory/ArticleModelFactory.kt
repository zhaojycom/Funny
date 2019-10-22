package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.ArticleRepository
import com.zhaojy.funny.model.ArticleModel

/**
 *@author: zhaojy
 *@data:On 2019/10/20.
 */
class ArticleModelFactory (private val repository: ArticleRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleModel(repository) as T
    }
}