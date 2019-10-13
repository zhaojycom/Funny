package com.zhaojy.funny.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.zhaojy.funny.R
import com.zhaojy.funny.bean.Plant
import com.zhaojy.funny.helper.StatusBarHelper

/**
 * @author: zhaojy
 * @data:On 2018/9/28.
 */

class PlantActivity : BaseActivity() {
    private var back: ImageView? = null
    private var webView: WebView? = null
    private var plantName: TextView? = null
    private var collect: ImageView? = null
    private var progressBar: ProgressBar? = null
    private lateinit var plant: Plant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.plant_layout)
        //设置状态栏字体颜色为深色
        StatusBarHelper.statusBarLightMode(this, StatusBarHelper.ANDROID_M)
        init()
    }

    override fun onDestroy() {
        webView?.let {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            val parent = it.parent
            if (parent != null) {
                (parent as ViewGroup).removeView(it)
            }
            it.stopLoading()
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            it.settings.javaScriptEnabled = false
            it.clearHistory()
            it.removeAllViews()
            it.destroy()
        }
        super.onDestroy()
    }


    private fun init() {
        findViewById()
        getIntentData()
        initPager()
        initListener()
    }

    private fun findViewById() {
        back = findViewById(R.id.back)
        webView = findViewById(R.id.webView)
        plantName = findViewById(R.id.plantName)
        collect = findViewById(R.id.collect)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun getIntentData() {
        val intent = intent
        plant = intent.getParcelableExtra(PLANT)
    }

    private fun initPager() {
        plantName?.text = plant.plantName
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webView?.let {
            val webSettings = webView!!.settings
            //设置可支持js
            webSettings.javaScriptEnabled = true
            //设置可在大视野范围内上下左右拖动，并且可以任意比例缩放
            webSettings.useWideViewPort = true
            // 设置可以支持缩放
            webSettings.setSupportZoom(true)
            webSettings.builtInZoomControls = true
            //不显示webview缩放按钮
            webSettings.displayZoomControls = false
            //设置默认加载的可视范围是大视野范围
            webSettings.loadWithOverviewMode = true
            //自适应屏幕
            webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            webView!!.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return false
                }
            }
            webView!!.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, progress: Int) {
                    if (progress == 100) {
                        progressBar!!.visibility = View.GONE
                    }
                }
            }
            webView!!.loadUrl(plant.articleUrl)
        }
    }

    private fun initListener() {
        back!!.setOnClickListener { this.finish() }
    }

    companion object {
        private const val TAG = "PlantActivity"
        const val PLANT = "plant"

        fun newInstance(activity: BaseActivity, plant: Plant) {
            val intent = Intent(activity, PlantActivity::class.java)
            intent.putExtra(PLANT, plant)
            activity.startActivity(intent)
        }
    }

}
