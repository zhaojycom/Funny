package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.PlantRepository
import com.zhaojy.funny.model.PlantModel

/**
 *@author: zhaojy
 *@data:On 2019/10/19.
 */
class PlantModelFactory(private val repository: PlantRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantModel(repository) as T
    }
}