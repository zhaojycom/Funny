package com.zhaojy.funny

import android.app.Application

import com.squareup.leakcanary.LeakCanary

/**
 * @author: zhaojy
 * @data:On 2019/9/28.
 */
class FunnyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLeakCanary()
    }

    /**
     * 初始化leakCanary
     */
    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            //1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

}
