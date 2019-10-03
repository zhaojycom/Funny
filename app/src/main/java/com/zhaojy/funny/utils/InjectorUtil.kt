package com.zhaojy.funny.utils

import com.zhaojy.funny.data.network.FunnyNetwork
import com.zhaojy.funny.data.repository.ClassifyRepository
import com.zhaojy.funny.data.repository.MainRepository
import com.zhaojy.funny.factory.ClassifyModelFactory
import com.zhaojy.funny.factory.MainModelFactory


object InjectorUtil {
    private fun getClassifyRepository() = ClassifyRepository.getInstance(
        FunnyNetwork.getInstance()
    )

    private fun getMainRepository() = MainRepository.getInstance(
        FunnyNetwork.getInstance()
    )

    fun getClassifyModelFactory() =
        ClassifyModelFactory(getClassifyRepository())

    fun getMainModelFactory() =
        MainModelFactory(getMainRepository())
}