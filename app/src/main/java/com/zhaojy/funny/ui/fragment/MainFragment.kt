package com.zhaojy.funny.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.youth.banner.Banner
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import com.zhaojy.funny.R
import com.zhaojy.funny.adapter.MainArticleAdapter
import com.zhaojy.funny.bean.ClassifyRequestParams
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.model.MainModel
import com.zhaojy.funny.utils.InjectorUtil
import okhttp3.RequestBody


/**
 * @author: zhaojy
 * @data:On 2018/9/15.
 */

class MainFragment : BaseFragment() {
    private var root: View? = null
    private var banner: Banner? = null
    private lateinit var mViewModel: MainModel
    private lateinit var mArticleRecycler: RecyclerView
    private lateinit var mMainArticleAdapter: MainArticleAdapter
    private var mOffset = 0
    private var mLastArticleListSize = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.mainpage, container, false)
            init()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (null != root) {
            (root!!.parent as ViewGroup).removeView(root)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun init() {
        mViewModel = ViewModelProviders.of(this, InjectorUtil.getMainModelFactory())
            .get(MainModel::class.java)
        findViewById()
        observe()
        initMainArticle()
        getBannerList()
        getArticleList()
    }

    private fun findViewById() {
        root?.let {
            banner = it.findViewById(R.id.banner)
            mArticleRecycler = it.findViewById(R.id.articleRecycler)
        }
    }

    private fun observe() {
        mViewModel.mBannerChanged.observe(this, Observer {
            //初始化轮播图
            initBanner()
        })
        mViewModel.mArticleChanged.observe(this, Observer {
            val size = mViewModel.mMainArticleList.size
            mMainArticleAdapter.notifyDataSetChanged()
            if (mViewModel.mMainArticleList.size == mLastArticleListSize) {
                mMainArticleAdapter.loadMoreEnd()
            } else {
                mMainArticleAdapter.loadMoreComplete()
            }
            mOffset += (size - mLastArticleListSize)
            mLastArticleListSize = size
        })
    }

    private fun initMainArticle() {
        mMainArticleAdapter = MainArticleAdapter(mViewModel.mMainArticleList)
        mArticleRecycler.adapter = mMainArticleAdapter
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mArticleRecycler.layoutManager = linearLayoutManager
        mMainArticleAdapter.setOnLoadMoreListener({ getArticleList() }, mArticleRecycler)
    }

    private fun getBannerList() {
        mViewModel.getBannerList()
    }

    private fun getArticleList() {
        val requestParams = ClassifyRequestParams()
        requestParams.limit = MainModel.LIMIT
        requestParams.offset = mOffset
        requestParams.classifyId = 1
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), Gson().toJson(requestParams)
        )
        mViewModel.getArticleList(body)
    }

    /**
     * 初始化轮播图
     */
    private fun initBanner() {
        banner?.let {
            it.setImages(mViewModel.mBannerImgList).setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                    val url = path as String
                    Glide.with(context)
                        .load(url)
                        .into(imageView)
                }
            })
            //设置轮播时间
            it.setDelayTime(6000)
            it.setBannerAnimation(Transformer.DepthPage)
            it.start()
        }
    }

    companion object {
        private val TAG = MainFragment::class.java.simpleName
    }

}