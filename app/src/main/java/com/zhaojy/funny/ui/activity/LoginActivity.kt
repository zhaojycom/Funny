package com.zhaojy.funny.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Window
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.zhaojy.funny.utils.DimUtils
import com.zhaojy.funny.utils.VerCodeUtils
import kotlinx.android.synthetic.main.login_layout.*
import java.lang.ref.WeakReference


/**
 *@author: zhaojy
 *@data:On 2019/10/4.
 */
class LoginActivity : BaseActivity() {
    private val vcu = VerCodeUtils.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(com.zhaojy.funny.R.layout.login_layout)
        setStatusBarTranparent()
        generateVerCode()
        dimBg()
    }

    private fun init() {
        findViewById()
    }

    private fun findViewById() {

    }

    /**
     * 模糊背景处理
     */
    private fun dimBg() {
        Glide.with(this)
            .load(com.zhaojy.funny.R.mipmap.login_bg)
            .asBitmap()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    glideAnimation: GlideAnimation<in Bitmap>
                ) {
                    val overlay = WeakReference(DimUtils.getDimBitmap(resource, DIM_RADIUS))
                    val drawable = BitmapDrawable(overlay.get())
                    bg.setBackground(drawable)
                }
            })
    }

    fun generateVerCode() {
        val codeBitmap = vcu.createBitmap()
        verCode.setImageBitmap(codeBitmap)
    }

    companion object {
        private val DIM_RADIUS = 8f

        fun newInstance(activity: BaseActivity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

}