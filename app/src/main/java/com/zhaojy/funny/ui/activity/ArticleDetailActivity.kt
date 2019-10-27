package com.zhaojy.funny.ui.activity

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.zhaojy.funny.bean.CollectionRequestParams
import com.zhaojy.funny.bean.HistoryRequestParams
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.data.bean.Article
import com.zhaojy.funny.data.livedata.UserLiveData
import com.zhaojy.funny.helper.StatusBarHelper
import com.zhaojy.funny.model.ArticleDetailModel
import com.zhaojy.funny.utils.InjectorUtil
import okhttp3.RequestBody


/**
 * @author: zhaojy
 * @data:On 2019/10/13.
 */
class ArticleDetailActivity : BaseActivity() {
    private lateinit var mArticle: Article
    private lateinit var mBack: ImageView
    private lateinit var mTitle: TextView
    private lateinit var mWebView: WebView
    private lateinit var mCollect: ImageView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mViewModel: ArticleDetailModel
    private val mUserLiveData = UserLiveData.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(com.zhaojy.funny.R.layout.article_detail_layout)
        //设置状态栏字体颜色为深色
        StatusBarHelper.statusBarLightMode(this, StatusBarHelper.ANDROID_M)
        init()
    }

    override fun onDestroy() {
        mWebView.let {
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
        mViewModel = ViewModelProviders.of(this, InjectorUtil.getArticleDetailModelFactory())
            .get(ArticleDetailModel::class.java)
        findViewById()
        getIntentData()
        initPager()
        initListener()
        recordHistory()
        observe()
    }

    private fun findViewById() {
        mBack = findViewById(com.zhaojy.funny.R.id.back)
        mTitle = findViewById(com.zhaojy.funny.R.id.title)
        mWebView = findViewById(com.zhaojy.funny.R.id.webView)
        mCollect = findViewById(com.zhaojy.funny.R.id.collect)
        mProgressBar = findViewById(com.zhaojy.funny.R.id.progressBar)
    }

    private fun getIntentData() {
        val intent = intent
        mArticle = intent.getParcelableExtra(ARTICLE)
    }

    private fun initPager() {
        mTitle.setText(mArticle.title)
        initWebView()
    }

    private fun observe() {
        mViewModel.mDataChanged.observe(this, Observer {
            initCollectIcon()
        })
        mUserLiveData.observe(this, Observer {
            getCollectInfo()
        })
    }

    private fun initWebView() {
        val webSettings = mWebView.settings
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
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        }
        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress == 100) {
                    mProgressBar.visibility = View.GONE
                }
            }
        }
        mWebView.loadUrl(mArticle.articleUrl)
    }

    private fun initListener() {
        mBack.setOnClickListener { this.finish() }
        mCollect.setOnClickListener { collectOrCancelCollect() }
    }

    private fun getCollectInfo() {
        val requestParams = CollectionRequestParams()
        requestParams.userPhone = mUserLiveData.value?.phone
        requestParams.collectSort = Constants.ARTICLE_SORT
        requestParams.collectId = mArticle.id
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), Gson().toJson(requestParams)
        )
        mViewModel.getCollectInfo(body)
    }

    private fun initCollectIcon() {
        if (mUserLiveData.value == null) {
            mCollect.visibility = View.GONE
            return
        }
        if (mViewModel.mCollect != null) {
            //如果该文章已收藏，则显示已收藏图标
            mCollect.setImageResource(com.zhaojy.funny.R.mipmap.collected)
        } else {
            //如果该文章未收藏，则显示未收藏图标
            mCollect.setImageResource(com.zhaojy.funny.R.mipmap.collect)
        }
    }

    private fun collectOrCancelCollect() {
        if (mViewModel.mCollect == null) {
            //收藏
            val requestParams = CollectionRequestParams()
            requestParams.userPhone = mUserLiveData.value?.phone
            requestParams.collectSort = Constants.ARTICLE_SORT
            requestParams.collectId = mArticle.id
            val body = RequestBody.create(
                okhttp3.MediaType.parse(
                    Constants.MEDIATYPE_JSON
                ), Gson().toJson(requestParams)
            )
            mViewModel.collect(body)
        } else {
            //取消收藏u
            val id: Int? = mViewModel.mCollect?.id
            mViewModel.cancelCollect(id)
        }
    }

    private fun recordHistory() {
        val requestParams = HistoryRequestParams()
        requestParams.browseSort = Constants.ARTICLE_SORT
        requestParams.userPhone = mUserLiveData.value?.phone
        requestParams.browseId = mArticle.id
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), Gson().toJson(requestParams)
        )
        mViewModel.recordHistory(body)
    }

    companion object {
        private const val TAG = "ArticleDetailActivity"
        const val ARTICLE = "mArticle"

        fun newInstance(activity: BaseActivity, article: Article) {
            val intent = Intent(activity, ArticleDetailActivity::class.java)
            intent.putExtra(ARTICLE, article)
            activity.startActivity(intent)
        }
    }
}
