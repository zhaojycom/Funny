package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.ArticleFragmentRepository
import com.zhaojy.funny.model.ArticleFragmentModel

/**
 *@author: zhaojy
 *@data:On 2019/10/27.
 */
class ArticleFragmentModelFactory (private val repository: ArticleFragmentRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleFragmentModel(repository) as T
    }
}