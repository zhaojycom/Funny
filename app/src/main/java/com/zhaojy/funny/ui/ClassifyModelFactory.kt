package com.zhaojy.funny.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.ClassifyRepository

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