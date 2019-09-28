package com.zhaojy.funny.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


/**
 * @author: zhaojy
 * @data:On 2019/9/21.
 */
open class BaseActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /**
     * 设置状态栏透明
     */
    fun setStatusBarTranparent() {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     */
    fun setStatusBarThemeColor(color: Int) {
        val window = this.window
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //设置状态栏颜色
            window.statusBarColor = color
        }

    }

}
