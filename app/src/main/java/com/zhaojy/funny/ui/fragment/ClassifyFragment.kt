package com.zhaojy.funny.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.zhaojy.funny.R
import com.zhaojy.funny.adapter.ClassifyPlantGridRvAdapter
import com.zhaojy.funny.adapter.LeftClassifyRvAdapter
import com.zhaojy.funny.bean.ClassifyRequestParams
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.ui.ClassifyModel
import com.zhaojy.funny.utils.InjectorUtil
import okhttp3.RequestBody


/**
 * @author: zhaojy
 * @data:On 2018/9/15.
 */

class ClassifyFragment : BaseFragment() {
    private var mRootView: View? = null
    private lateinit var mLeftClassifyRv: RecyclerView;
    private lateinit var mPlantGridRv: RecyclerView;
    private lateinit var mClassifyRvAdapter: LeftClassifyRvAdapter
    private lateinit var mPlantGridRvAdapter: ClassifyPlantGridRvAdapter
    private lateinit var mViewModel: ClassifyModel
    private var mOffset = 0
    private var mSelectedClassifyId = 0
    private var mLastPlantListSize = 0

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.classify, container, false)
        init()
        return mRootView
    }

    private fun init() {
        mViewModel = ViewModelProviders.of(this, InjectorUtil.getClassifyModelFactory())
            .get(ClassifyModel::class.java)
        findViewById()
        initLeftClassifyRv()
        initPlantGridRv()
        observe()
        getPlantClassifyList()
    }

    private fun findViewById() {
        mLeftClassifyRv = mRootView!!.findViewById(com.zhaojy.funny.R.id.leftClassifyRv)
        mPlantGridRv = mRootView!!.findViewById(com.zhaojy.funny.R.id.plantGridRv)
    }

    private fun initLeftClassifyRv() {
        mClassifyRvAdapter = LeftClassifyRvAdapter(mViewModel.classifyList)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mLeftClassifyRv.layoutManager = linearLayoutManager
        mLeftClassifyRv.adapter = mClassifyRvAdapter
        mClassifyRvAdapter.setOnItemClickListener { adapter, view, position ->
            selectClassify(position)
        }
    }

    private fun selectClassify(position: Int) {
        if (position >= mViewModel.classifyList.size) {
            return
        }
        val cb = mViewModel.classifyList[position]
        for (item in mViewModel.classifyList) {
            item.selected = false
        }
        cb.selected = true
        mClassifyRvAdapter.notifyDataSetChanged()
        if (mSelectedClassifyId != cb.id) {
            mViewModel.classifyPlantList.clear()
            mOffset = 0
            mLastPlantListSize = 0
        }
        mSelectedClassifyId = cb.id
        getClassifyPlantList()
    }

    private fun initPlantGridRv() {
        mPlantGridRvAdapter = ClassifyPlantGridRvAdapter(mViewModel.classifyPlantList)
        val gridLayoutManager = GridLayoutManager(activity, 3)
        mPlantGridRv.layoutManager = gridLayoutManager
        mPlantGridRv.adapter = mPlantGridRvAdapter
        mPlantGridRvAdapter.setOnItemClickListener { adapter, view, position -> }
        mPlantGridRvAdapter.setOnLoadMoreListener({ getClassifyPlantList() }, mPlantGridRv)
    }

    private fun observe() {
        mViewModel.classifyDataChanged.observe(this, Observer {
            mClassifyRvAdapter.notifyDataSetChanged()
            selectClassify(0)
        })
        mViewModel.classifyPlantChanged.observe(this, Observer {
            val size = mViewModel.classifyPlantList.size
            /* mPlantGridRvAdapter.notifyItemRangeInserted(
                 mLastPlantListSize,
                 size - mLastPlantListSize
             )*/
            mPlantGridRvAdapter.notifyDataSetChanged()
            if (mViewModel.classifyPlantList.size == mLastPlantListSize) {
                mPlantGridRvAdapter.loadMoreEnd()
            } else {
                mPlantGridRvAdapter.loadMoreComplete()
            }
            mLastPlantListSize = size
            mOffset += size
        })
    }

    private fun getPlantClassifyList() {
        mViewModel.getPlantClassifyList()
    }

    private fun getClassifyPlantList() {
        val requestParams = ClassifyRequestParams()
        requestParams.limit = LIMIT
        requestParams.offset = mOffset
        requestParams.classifyId = mSelectedClassifyId
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), Gson().toJson(requestParams)
        )
        mViewModel.getClassifyPlantList(body)
    }

    companion object {
        private val TAG = ClassifyFragment::class.java.simpleName
        private val LIMIT = 9
    }

}

