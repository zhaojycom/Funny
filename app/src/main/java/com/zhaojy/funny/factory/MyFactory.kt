package com.zhaojy.funny.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhaojy.funny.data.repository.LoginRepository
import com.zhaojy.funny.data.repository.MyRepository
import com.zhaojy.funny.model.MyModel
import kotlin.math.log

/**
 *@author: zhaojy
 *@data:On 2019/10/4.
 */
class MyModelFactory(
    private val myRepository: MyRepository,
    private val loginRepository: LoginRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyModel(myRepository, loginRepository) as T
    }
}