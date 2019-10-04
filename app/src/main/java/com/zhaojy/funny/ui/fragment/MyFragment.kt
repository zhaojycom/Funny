package com.zhaojy.funny.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProviders
import com.zhaojy.funny.R
import com.zhaojy.funny.model.MyModel
import com.zhaojy.funny.ui.activity.BaseActivity
import com.zhaojy.funny.ui.activity.LoginActivity
import com.zhaojy.funny.utils.InjectorUtil


/**
 * @author: zhaojy
 * @data:On 2018/9/15.
 */

class MyFragment : BaseFragment() {
    private var mRoot: View? = null
    private lateinit var mViewModel: MyModel
    private var myBanner: RelativeLayout? = null
    private var mAvatar: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.my, container, false)
            init()
        }

        return mRoot
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRoot?.let {
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
        //初始化指示条
        initIndicator()
        setViewPager()
        initListener()
        //登录
        login()
    }

    private fun findViewById() {
        mRoot?.let {
            mAvatar = it.findViewById(R.id.avatar)
        }
    }

    /**
     * 设置ViewPager
     */
    private fun setViewPager() {

    }

    /**
     * 初始化指示条
     */
    private fun initIndicator() {

    }

    private fun initListener() {
        mAvatar?.setOnClickListener({
            LoginActivity.newInstance(activity as BaseActivity)
        })
    }

    /**
     * 登录
     */
    private fun login() {

    }

    companion object {
        private val TAG = MyFragment::class.java.simpleName
    }

}