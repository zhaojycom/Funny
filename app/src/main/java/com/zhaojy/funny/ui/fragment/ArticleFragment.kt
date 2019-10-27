package com.zhaojy.funny.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.zhaojy.funny.R
import com.zhaojy.funny.adapter.ArticleRvAdapter
import com.zhaojy.funny.bean.ClassifyRequestParams
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.model.ArticleFragmentModel
import com.zhaojy.funny.ui.activity.ArticleDetailActivity
import com.zhaojy.funny.ui.activity.BaseActivity
import com.zhaojy.funny.utils.InjectorUtil
import okhttp3.RequestBody

/**
 *@author: zhaojy
 *@data:On 2019/10/26.
 */
class ArticleFragment : BaseFragment() {
    private lateinit var mRootView: View
    private lateinit var mViewModel: ArticleFragmentModel
    private lateinit var mArticleRecycler: RecyclerView
    private lateinit var mArticleRvAdapter: ArticleRvAdapter
    private var mOffset = 0
    private var mLastArticleListSize = 0

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.article_fragment_layout, container, false)
        init()
        return mRootView
    }

    private fun init() {
        mViewModel = ViewModelProviders.of(this, InjectorUtil.getArticleFragmentFactory())
            .get(ArticleFragmentModel::class.java)
        findViewById()
        observe()
        initArticleRv()
        getArticleList()
    }

    private fun findViewById() {
        mRootView.let {
            mArticleRecycler = it.findViewById(R.id.article_rv)
        }
    }

    private fun observe() {
        mViewModel.mDataChanged.observe(this, Observer {
            val size = mViewModel.mArticleList.size
            mArticleRvAdapter.notifyItemChanged(mLastArticleListSize, size)
            if (mViewModel.mArticleList.size == mLastArticleListSize) {
                mArticleRvAdapter.loadMoreEnd()
            } else {
                mArticleRvAdapter.loadMoreComplete()
            }
            mOffset += (size - mLastArticleListSize)
            mLastArticleListSize = size
        })
    }

    private fun initArticleRv() {
        mArticleRvAdapter = ArticleRvAdapter(mViewModel.mArticleList)
        mArticleRecycler.adapter = mArticleRvAdapter
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mArticleRecycler.layoutManager = linearLayoutManager
        mArticleRvAdapter.setOnLoadMoreListener({ getArticleList() }, mArticleRecycler)
        mArticleRvAdapter.setOnItemClickListener { adapter, view, position ->
            val article = mViewModel.mArticleList.get(position)
            ArticleDetailActivity.newInstance(activity as BaseActivity, article)
        }
    }

    private fun getArticleList() {
        val requestParams = ClassifyRequestParams()
        requestParams.limit = ArticleFragmentModel.LIMIT
        requestParams.offset = mOffset
        requestParams.classifyId = 1
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), Gson().toJson(requestParams)
        )
        mViewModel.getArticleList(body)
    }
}
