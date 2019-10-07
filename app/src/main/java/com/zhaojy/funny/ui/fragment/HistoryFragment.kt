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
import com.zhaojy.funny.R
import com.zhaojy.funny.adapter.HistoryRvAdapter
import com.zhaojy.funny.bean.ReadHistoryCollectionRequestParams
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.data.bean.User
import com.zhaojy.funny.data.livedata.UserLiveData
import com.zhaojy.funny.model.HistoryModel
import com.zhaojy.funny.utils.InjectorUtil
import okhttp3.RequestBody

/**
 * @author: zhaojy
 * @data:On 2018/9/16.
 */

class HistoryFragment : BaseFragment() {
    private var mRoot: View? = null
    private lateinit var mViewModel: HistoryModel
    private val mUserLiveData = UserLiveData.get()
    private lateinit var mHistoryRvAdapter: HistoryRvAdapter
    private lateinit var mHistoryRv: RecyclerView
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    private var mOffset = 0
    private var mLastHistoryListSize = 0

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.history, container, false)
            init()
        }

        return mRoot
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRoot?.let {
            (it.parent as ViewGroup).removeView(mRoot)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun init() {
        mViewModel = ViewModelProviders.of(this, InjectorUtil.getHistoryModelFactory())
            .get(HistoryModel::class.java)
        findViewById()
        observe()
        initHistoryRv()
        initRefreshLayout()
    }

    private fun findViewById() {
        mRoot?.let {
            mHistoryRv = it.findViewById(R.id.recyclerView)
            mRefreshLayout = it.findViewById(R.id.refreshLayout)
        }
    }

    private fun observe() {
        mUserLiveData.observe(this, Observer {
            refresh()
        })
        mViewModel.mHistoryListChanged.observe(this, Observer {
            val size = mViewModel.mHistoryList.size
            mHistoryRvAdapter.notifyDataSetChanged()
            if (mViewModel.mHistoryList.size == mLastHistoryListSize) {
                mHistoryRvAdapter.loadMoreEnd()
            } else {
                mHistoryRvAdapter.loadMoreComplete()
            }
            mOffset += (size - mLastHistoryListSize)
            mLastHistoryListSize = size
            mRefreshLayout.isRefreshing = false
        })
    }

    private fun getHistoryList() {
        if (mViewModel.mHistoryList.size == 0) {
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
        mRefreshLayout.setColorSchemeResources(R.color.theme)
        mRefreshLayout.setOnRefreshListener {
            refresh()
        }
    }

    private fun refresh() {
        mViewModel.mHistoryList.clear()
        mOffset = 0
        getHistoryList()
    }

    private fun initHistoryRv() {
        mHistoryRvAdapter = HistoryRvAdapter(mViewModel.mHistoryList)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mHistoryRv.layoutManager = linearLayoutManager
        mHistoryRv.adapter = mHistoryRvAdapter
        mHistoryRvAdapter.setOnItemClickListener { adapter, view, position ->

        }
        mHistoryRvAdapter.setOnLoadMoreListener({
            getHistoryList()
        }, mHistoryRv)
    }

    companion object {
        private val TAG = HistoryFragment::class.java.simpleName
        private const val LIMIT = 6
    }

}
