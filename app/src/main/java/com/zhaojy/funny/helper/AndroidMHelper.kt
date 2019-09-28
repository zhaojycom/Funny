package com.zhaojy.funny.helper

import android.app.Activity
import android.os.Build
import android.view.View

/**
 * Android 6.0+系统状态栏字体颜色帮助类
 *
 * @author: zhaojy
 * @data:On 2018/9/16.
 */

class AndroidMHelper : IHelper {
    /**
     * @return if version is lager than M
     */
    override fun setStatusBarLightMode(activity: Activity, isFontColorDark: Boolean): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isFontColorDark) {
                // 沉浸式
                //activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                //非沉浸式
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                //非沉浸式
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
            return true
        }
        return false
    }

}
