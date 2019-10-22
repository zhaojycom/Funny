package com.zhaojy.funny.ui.fragment

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.signature.StringSignature
import com.zhaojy.funny.R
import com.zhaojy.funny.adapter.ViewPagerAdapter
import com.zhaojy.funny.bean.Count
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.data.bean.User
import com.zhaojy.funny.data.livedata.CollectLiveData
import com.zhaojy.funny.data.livedata.HistoryLiveData
import com.zhaojy.funny.data.livedata.UserLiveData
import com.zhaojy.funny.data.network.ServiceCreator
import com.zhaojy.funny.model.MyModel
import com.zhaojy.funny.ui.activity.BaseActivity
import com.zhaojy.funny.ui.activity.LoginActivity
import com.zhaojy.funny.utils.InjectorUtil
import kotlinx.android.synthetic.main.my.*


/**
 * @author: zhaojy
 * @data:On 2018/9/15.
 */

class MyFragment : BaseFragment() {
    private lateinit var mRoot: View
    private lateinit var mViewModel: MyModel
    private lateinit var myBanner: RelativeLayout
    private lateinit var mAvatar: ImageView
    private val mUserLiveData = UserLiveData.get()
    private lateinit var mVpAdapter: ViewPagerAdapter
    private lateinit var mViewPager: ViewPager
    private lateinit var mCollectSum: TextView
    private lateinit var mHistorySum: TextView
    private var mCollectLiveData = CollectLiveData.get()
    private var mHistoryLiveData = HistoryLiveData.get()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRoot = inflater.inflate(R.layout.my, container, false)
        init()
        return mRoot
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRoot.let {
            (it.parent as ViewGroup).removeView(it)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun init() {
        mViewModel = ViewModelProviders.of(this, InjectorUtil.getMyModelFactory())
            .get(MyModel::class.java)
        findViewById()
        observe()
        //初始化指示条
        initIndicator()
        initViewPager()
        initListener()
        //登录
        login()
    }

    private fun findViewById() {
        mRoot.let {
            mAvatar = it.findViewById(R.id.avatar)
            myBanner = it.findViewById(R.id.myBanner)
            mViewPager = it.findViewById(R.id.viewPager)
            mCollectSum = it.findViewById(R.id.collectSum)
            mHistorySum = it.findViewById(R.id.historySum)
        }
    }

    private fun login() {
        val phoneNumber = "18774512067"
        mViewModel.login(phoneNumber)
    }

    private fun observe() {
        mUserLiveData.observe(this, Observer {
            val user: User? = mUserLiveData.value
            user?.let {
                updateUserInfo(user)
            }
        })
        mViewModel.mCollectSumLiveData.observe(this, Observer {
            updateCollectSum()
        })
        mViewModel.mHistorySumLiveData.observe(this, Observer {
            updateHistorySum()
        })
        mCollectLiveData.observe(this, Observer {
            getCollectSum()
        })
        mHistoryLiveData.observe(this, Observer {
            getHistorySum()
        })
    }

    private fun updateCollectSum() {
        val count = mViewModel.mCollectCount as Count
        count.let {
            if (it.count > 99) {
                mCollectSum.text = Constants.COLLECT + "\n" + "99+"
            } else {
                mCollectSum.text = Constants.COLLECT + "\n" + it.count
            }
        }
    }

    private fun updateHistorySum() {
        val count = mViewModel.mHistoryCount as Count
        count.let {
            if (it.count > 99) {
                mHistorySum.text = Constants.HISTORY + "\n" + "99+"
            } else {
                mHistorySum.text = Constants.HISTORY + "\n" + it.count
            }
        }
    }

    private fun updateUserInfo(user: User) {
        getCollectSum()
        getHistorySum()
        //设置头像
        Glide.with(activity)
            .load(ServiceCreator.BASE_URL + user.avatar)
            .asBitmap()
            .signature(
                StringSignature(
                    System.currentTimeMillis().toString()
                )
            )
            .placeholder(R.mipmap.icon)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    glideAnimation: GlideAnimation<in Bitmap>
                ) {
                    avatar?.setImageBitmap(resource)
                }
            })
        //设置背景
        Glide.with(activity)
            .load(ServiceCreator.BASE_URL + user.banner)
            .asBitmap()
            .signature(
                StringSignature(
                    System.currentTimeMillis().toString()
                )
            )
            .placeholder(com.zhaojy.funny.R.mipmap.banner)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    glideAnimation: GlideAnimation<in Bitmap>
                ) {
                    val drawable = BitmapDrawable(this@MyFragment.resources, resource)
                    myBanner.background = drawable
                }
            })
    }

    private fun getCollectSum() {
        val user: User = mUserLiveData.value as User
        mViewModel.getCollectSum(user.phone as String)
    }

    private fun getHistorySum() {
        val user: User = mUserLiveData.value as User
        mViewModel.getHistorySum(user.phone as String)
    }

    private fun initViewPager() {
        mVpAdapter = ViewPagerAdapter(childFragmentManager)
        mViewPager.adapter = mVpAdapter
        val list = ArrayList<BaseFragment>()
        list.add(CollectFragment())
        list.add(HistoryFragment())
        mVpAdapter.setList(list)

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(pos: Int) {

            }

            override fun onPageScrolled(pos: Int, offsetPercentage: Float, offsetPixel: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })
    }

    /**
     * 初始化指示条
     */
    private fun initIndicator() {

    }

    private fun initListener() {
        mAvatar.setOnClickListener {
            LoginActivity.newInstance(activity as BaseActivity)
            if (mUserLiveData.value == null) {

            } else {

            }
        }
    }

    companion object {
        private val TAG = MyFragment::class.java.simpleName
    }

}