package com.zhaojy.funny.utils

import com.zhaojy.funny.data.ClassifyRepository
import com.zhaojy.funny.data.network.FunnyNetwork
import com.zhaojy.funny.ui.ClassifyModelFactory


object InjectorUtil {
    private fun getClassifyRepository() = ClassifyRepository.getInstance(
        FunnyNetwork.getInstance()
    )

    fun getClassifyModelFactory() = ClassifyModelFactory(getClassifyRepository())

}