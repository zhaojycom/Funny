package com.zhaojy.funny.utils

import com.zhaojy.funny.data.network.FunnyNetwork
import com.zhaojy.funny.data.repository.*
import com.zhaojy.funny.factory.*


object InjectorUtil {
    private fun getClassifyRepository() = ClassifyRepository.getInstance(
        FunnyNetwork.getInstance()
    )

    private fun getMainRepository() = MainRepository.getInstance(
        FunnyNetwork.getInstance()
    )

    private fun getMyRepository() = MyRepository.getInstance(
        FunnyNetwork.getInstance()
    )

    private fun getLoginRepository() = LoginRepository.getInstance(
        FunnyNetwork.getInstance()
    )

    private fun getCollectRepository() = CollectRepository.getInstance(
        FunnyNetwork.getInstance()
    )

    private fun getHistoryRepository() = HistoryRepository.getInstance(
        FunnyNetwork.getInstance()
    )

    fun getClassifyModelFactory() =
        ClassifyModelFactory(getClassifyRepository())

    fun getMainModelFactory() =
        MainModelFactory(getMainRepository())

    fun getMyModelFactory() = MyModelFactory(getMyRepository(), getLoginRepository())

    fun getLoginModelFactory() = LoginModelFactory(getLoginRepository())

    fun getCollectModelFactory() = CollectModelFactory(getCollectRepository())

    fun getHistoryModelFactory() = HistoryModelFactory(getHistoryRepository())
}