package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.CollectRepository
import com.zhaojy.funny.model.CollectModel

/**
 *@author: zhaojy
 *@data:On 2019/10/6.
 */
class CollectModelFactory(private val repository: CollectRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CollectModel(repository) as T
    }
}