package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.MainRepository
import com.zhaojy.funny.model.MainModel

/**
 *@author: zhaojy
 *@data:On 2019/10/2.
 */
class MainModelFactory(private val repository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainModel(repository) as T
    }
}