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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.zhaojy.funny.adapter.CollectRvAdapter
import com.zhaojy.funny.bean.Plant
import com.zhaojy.funny.bean.ReadHistoryCollectionRequestParams
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.data.bean.Article
import com.zhaojy.funny.data.bean.User
import com.zhaojy.funny.data.livedata.UserLiveData
import com.zhaojy.funny.model.CollectModel
import com.zhaojy.funny.ui.activity.ArticleDetailActivity
import com.zhaojy.funny.ui.activity.BaseActivity
import com.zhaojy.funny.ui.activity.PlantActivity
import com.zhaojy.funny.utils.InjectorUtil
import okhttp3.RequestBody


/**
 * @author: zhaojy
 * @data:On 2018/9/16.
 */

class CollectFragment : BaseFragment() {
    private var mRoot: View? = null
    private lateinit var mViewModel: CollectModel
    private val mUserLiveData = UserLiveData.get()
    private lateinit var mCollectRvAdapter: CollectRvAdapter
    private lateinit var mCollectRv: RecyclerView
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    private var mOffset = 0
    private var mLastCollectListSize = 0
    private var mLoading: Boolean = false

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRoot = inflater.inflate(com.zhaojy.funny.R.layout.collect, container, false)

        init()
        return mRoot
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onResume() {
        super.onResume()

    }

    private fun init() {
        mViewModel = ViewModelProviders.of(this, InjectorUtil.getCollectModelFactory())
            .get(CollectModel::class.java)
        findViewById()
        observe()
        initCollectRv()
        initRefreshLayout()
    }

    private fun findViewById() {
        mRoot?.let {
            mCollectRv = it.findViewById(com.zhaojy.funny.R.id.recyclerView)
            mRefreshLayout = it.findViewById(com.zhaojy.funny.R.id.refreshLayout)
        }
    }

    private fun observe() {
        mUserLiveData.observe(this, Observer {
            refresh()
        })
        mViewModel.mCollectListChanged.observe(this, Observer {
            val size = mViewModel.mCollectList.size
            mCollectRvAdapter.notifyItemChanged(mLastCollectListSize, size)
            if (mViewModel.mCollectList.size == mLastCollectListSize) {
                mCollectRvAdapter.loadMoreEnd()
            } else {
                mCollectRvAdapter.loadMoreComplete()
            }
            mOffset += (size - mLastCollectListSize)
            mLastCollectListSize = size
            mRefreshLayout.isRefreshing = false
            mLoading = false
        })
    }

    @Synchronized
    private fun getCollectList() {
        if (mLoading) {
            return
        }
        mLoading = true
        if (mViewModel.mCollectList.size == 0) {
            mRefreshLayout.isRefreshing = true
        }
        val requestParams = ReadHistoryCollectionRequestParams()
        requestParams.offset = mOffset
        requestParams.limit = LIMIT
        val user: User = mUserLiveData.value as User
        requestParams.userPhone = user.phone
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), Gson().toJson(requestParams)
        )
        mViewModel.getCollectList(body)
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(com.zhaojy.funny.R.color.theme)
        mRefreshLayout.setOnRefreshListener {
            refresh()
        }
    }

    private fun refresh() {
        mViewModel.mCollectList.clear()
        mOffset = 0
        getCollectList()
    }

    private fun initCollectRv() {
        mCollectRvAdapter = CollectRvAdapter(mViewModel.mCollectList)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mCollectRv.layoutManager = linearLayoutManager
        mCollectRv.adapter = mCollectRvAdapter
        mCollectRvAdapter.setOnItemClickListener { adapter, view, position ->
            val collect = mViewModel.mCollectList.get(position)
            when (collect.sort) {
                Constants.ARTICLE -> {
                    val article = Article()
                    article.title = collect.title
                    article.articleUrl = collect.articleUrl
                    ArticleDetailActivity.newInstance(activity as BaseActivity, article)
                }
                Constants.PLANT -> {
                    val plant = Plant()
                    plant.plantName = collect.title
                    plant.articleUrl = collect.articleUrl
                    PlantActivity.newInstance(activity as BaseActivity, plant)
                }
            }
        }
        mCollectRvAdapter.setOnLoadMoreListener({
            getCollectList()
        }, mCollectRv)
    }

    companion object {
        private val TAG = CollectFragment::class.java.simpleName
        private const val LIMIT = 6
    }

}
