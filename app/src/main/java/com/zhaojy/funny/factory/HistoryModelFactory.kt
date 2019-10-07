package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.HistoryRepository
import com.zhaojy.funny.model.HistoryModel

/**
 *@author: zhaojy
 *@data:On 2019/10/7.
 */
class HistoryModelFactory(private val repository: HistoryRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HistoryModel(repository) as T
    }
}