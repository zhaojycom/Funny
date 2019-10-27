package com.zhaojy.funny.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import com.zhaojy.funny.R
import com.zhaojy.funny.helper.StatusBarHelper

/**
 * @author: zhaojy
 * @data:On 2018/10/7.
 */
class ArticleClassifyActivity : BaseActivity() {
    private var back: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.article_classify_layout)
        //设置状态栏字体颜色为深色
        StatusBarHelper.statusBarLightMode(this, StatusBarHelper.ANDROID_M)
        init()
    }

    private fun init() {
        findViewById()
        initListener()
    }

    private fun findViewById() {
        back = findViewById(R.id.back)

    }

    private fun initListener() {
        back?.setOnClickListener {
            this.finish()
        }
    }

    companion object {
        private const val TAG = "ArticleClassifyActivity"

        fun newInstance(activity: BaseActivity) {
            val intent = Intent(activity, ArticleClassifyActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
