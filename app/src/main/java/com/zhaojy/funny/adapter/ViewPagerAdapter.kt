package com.zhaojy.funny.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author: zhaojy
 * @data:On 2019/9/16.
 */
class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var list: List<Fragment>? = null

    fun setList(list: List<Fragment>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return list!![position]
    }

    override fun getCount(): Int {
        return if (list != null) list!!.size else 0
    }
}