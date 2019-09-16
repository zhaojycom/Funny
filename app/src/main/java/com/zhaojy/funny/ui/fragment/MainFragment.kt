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

class MainFragment : BaseFragment() {
    private var root: View? = null

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
        findViewById()

    }

    private fun findViewById() {

    }

    /**
     * 设置轮播图
     */
    private fun setBanner() {

    }

    companion object {
        private val TAG = MainFragment::class.java.simpleName
    }

}