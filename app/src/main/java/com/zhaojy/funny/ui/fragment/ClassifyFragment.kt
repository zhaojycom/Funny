package com.zhaojy.funny.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.zhaojy.funny.R


/**
 * @author: zhaojy
 * @data:On 2018/9/15.
 */

class ClassifyFragment : BaseFragment() {
    private var root: View? = null

    override fun onDestroy() {
        super.onDestroy()

    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.classify, container, false)
        init()
        return root
    }

    override fun onStart() {
        super.onStart()

    }

    private fun init() {
        findViewById()

    }

    private fun findViewById() {

    }

    companion object {
        private val TAG = ClassifyFragment::class.java.simpleName
    }

}