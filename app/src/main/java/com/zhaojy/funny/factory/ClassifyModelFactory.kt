package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.ClassifyRepository
import com.zhaojy.funny.model.ClassifyModel

/**
 *@author: zhaojy
 *@data:On 2019/9/22.
 */
class ClassifyModelFactory(private val repository: ClassifyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassifyModel(repository) as T
    }
}