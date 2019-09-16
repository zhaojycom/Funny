package com.zhaojy.funny.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zhaojy.funny.R


/**
 * @author: zhaojy
 * @data:On 2018/9/15.
 */

class MyFragment : BaseFragment() {
    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.my, container, false)
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


    override fun onResume() {
        super.onResume()

    }

    private fun init() {
        findViewById()
        //初始化指示条
        initIndicator()
        setViewPager()
        //登录
        login()
    }

    private fun findViewById() {

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

    /**
     * 登录
     */
    private fun login() {

    }

    companion object {
        private val TAG = MyFragment::class.java.simpleName
    }

}