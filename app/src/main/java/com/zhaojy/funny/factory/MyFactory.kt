package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.MyRepository
import com.zhaojy.funny.model.MyModel

/**
 *@author: zhaojy
 *@data:On 2019/10/4.
 */
class MyModelFactory(private val repository: MyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyModel(repository) as T
    }
}