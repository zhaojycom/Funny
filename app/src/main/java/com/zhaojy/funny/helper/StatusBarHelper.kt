package com.zhaojy.funny.helper

import android.app.Activity

import androidx.annotation.IntDef

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @author: zhaojy
 * @data:On 2018/9/16.
 */

object StatusBarHelper {
    const val OTHER = -1
    const val MIUI = 1
    const val FLYME = 2
    const val ANDROID_M = 3

    @IntDef(OTHER, MIUI, FLYME, ANDROID_M)
    @Retention(RetentionPolicy.SOURCE)
    annotation class SystemType

    /**
     * 设置状态栏黑色字体图标，
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @return 1:MIUI 2:Flyme 3:android6.0
     */
    fun statusBarLightMode(activity: Activity): Int {
        @SystemType var result = 0
        if (MIUIHelper().setStatusBarLightMode(activity, true)) {
            result = MIUI
        } else if (FlymeHelper().setStatusBarLightMode(activity, true)) {
            result = FLYME
        } else if (AndroidMHelper().setStatusBarLightMode(activity, true)) {
            result = ANDROID_M
        }
        return result
    }

    /**
     * 已知系统类型时，设置状态栏黑色字体图标。
     * 适配4.4以上版本MIUI6、Flyme和6.0以上版本其他Android
     *
     * @param type 1:MIUI 2:Flyme 3:android6.0
     */
    fun statusBarLightMode(activity: Activity, @SystemType type: Int) {
        statusBarMode(activity, type, true)
    }

    /**
     * 清除MIUI或flyme或6.0以上版本状态栏黑色字体
     */
    fun statusBarDarkMode(activity: Activity, @SystemType type: Int) {
        statusBarMode(activity, type, false)
    }

    private fun statusBarMode(activity: Activity, @SystemType type: Int, isFontColorDark: Boolean) {
        if (type == MIUI) {
            MIUIHelper().setStatusBarLightMode(activity, isFontColorDark)
        } else if (type == FLYME) {
            FlymeHelper().setStatusBarLightMode(activity, isFontColorDark)
        } else if (type == ANDROID_M) {
            AndroidMHelper().setStatusBarLightMode(activity, isFontColorDark)
        }
    }

}


