package com.zhaojy.funny.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.youth.banner.Banner
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import com.zhaojy.funny.R
import com.zhaojy.funny.model.MainModel
import com.zhaojy.funny.ui.activity.ArticleClassifyActivity
import com.zhaojy.funny.ui.activity.BaseActivity
import com.zhaojy.funny.utils.InjectorUtil

/**
 * @author: zhaojy
 * @data:On 2018/9/15.
 */

class MainFragment : BaseFragment() {
    private var mRootView: View? = null
    private var mBanner: Banner? = null
    private var mArticle: View? = null
    private lateinit var mViewModel: MainModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.mainpage, container, false)
            init()
        }

        return mRootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (null != mRootView) {
            (mRootView!!.parent as ViewGroup).removeView(mRootView)
        }
    }


    private fun init() {
        mViewModel = ViewModelProviders.of(this, InjectorUtil.getMainModelFactory())
            .get(MainModel::class.java)
        findViewById()
        observe()
        initListener()
        getBannerList()
    }

    private fun findViewById() {
        mRootView?.let {
            mBanner = it.findViewById(R.id.banner)
            mArticle = it.findViewById(R.id.article)
        }
    }

    private fun initListener() {
        mArticle?.setOnClickListener {
            ArticleClassifyActivity.newInstance(activity as BaseActivity)
        }
    }

    private fun observe() {
        mViewModel.mBannerChanged.observe(this, Observer {
            //初始化轮播图
            initBanner()
        })
    }

    private fun getBannerList() {
        mViewModel.getBannerList()
    }

    /**
     * 初始化轮播图
     */
    private fun initBanner() {
        mBanner?.let {
            it.setImages(mViewModel.mBannerImgList).setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                    val url = path as String
                    Glide.with(context)
                        .load(url)
                        .into(imageView)
                }
            })
            //设置轮播时间
            it.setDelayTime(BANNER_DELAY_TIME)
            it.setBannerAnimation(Transformer.DepthPage)
            it.start()
        }
    }

    companion object {
        private val TAG = MainFragment::class.java.simpleName
        private const val BANNER_DELAY_TIME = 6000
    }

}