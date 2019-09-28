package com.zhaojy.funny.helper

import android.app.Activity

/**
 * @author: zhaojy
 * @data:On 2018/9/16.
 */

interface IHelper {
    fun setStatusBarLightMode(activity: Activity, isFontColorDark: Boolean): Boolean
}
