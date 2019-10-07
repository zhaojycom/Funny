package com.zhaojy.funny.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.zhaojy.funny.R
import com.zhaojy.funny.data.bean.User
import com.zhaojy.funny.model.LoginModel
import com.zhaojy.funny.utils.DimUtils
import com.zhaojy.funny.utils.InjectorUtil
import com.zhaojy.funny.utils.VerCodeUtils
import kotlinx.android.synthetic.main.login_layout.*
import java.lang.ref.WeakReference


/**
 *@author: zhaojy
 *@data:On 2019/10/4.
 */
class LoginActivity : BaseActivity() {
    private val mVercodeUtil = VerCodeUtils.instance
    private lateinit var mLoginModel: LoginModel
    private lateinit var mPhoneInput: EditText
    private lateinit var mLoginBtn: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.login_layout)
        init()
    }

    private fun init() {
        mLoginModel = ViewModelProviders.of(this, InjectorUtil.getLoginModelFactory())
            .get(LoginModel::class.java)
        setStatusBarTranparent()
        findViewById()
        observe()
        initListener()
        generateVerCode()
        dimBg()
    }

    private fun findViewById() {
        mLoginBtn = findViewById(R.id.login_btn)
        mPhoneInput = findViewById(R.id.phoneInput)
    }

    private fun observe() {
        mLoginModel.mUserLiveData.observe(this, Observer {
            val user: User? = mLoginModel.mUserLiveData.value
            user?.let {
                //finish()
            }
        })
    }

    private fun initListener() {
        mLoginBtn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val phoneNumber = mPhoneInput.text.toString()
        mLoginModel.login(phoneNumber)
    }

    /**
     * 背景模糊
     */
    private fun dimBg() {
        Glide.with(this)
            .load(R.mipmap.login_bg)
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
        val codeBitmap = mVercodeUtil.createBitmap()
        verCode.setImageBitmap(codeBitmap)
    }

    companion object {
        private const val TAG = "LoginActivity"
        private const val DIM_RADIUS = 4f

        fun newInstance(activity: BaseActivity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

}