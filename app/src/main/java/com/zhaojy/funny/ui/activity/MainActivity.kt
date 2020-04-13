package com.zhaojy.funny.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhaojy.funny.R
import com.zhaojy.funny.adapter.ViewPagerAdapter
import com.zhaojy.funny.databinding.ActivityMainBinding
import com.zhaojy.funny.helper.StatusBarHelper
import com.zhaojy.funny.ui.fragment.ClassifyFragment
import com.zhaojy.funny.ui.fragment.MainFragment
import com.zhaojy.funny.ui.fragment.MyFragment
import java.util.*

class MainActivity : BaseActivity() {
    private lateinit var mBottomNavigationView: BottomNavigationView
    private lateinit var mViewPager: ViewPager
    private lateinit var mViewPagerAdapter: ViewPagerAdapter
    private var mMenuItem: MenuItem? = null
    private lateinit var mViewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        mViewDataBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        title = "Title"
        //设置状态栏字体颜色为深色
        StatusBarHelper.statusBarLightMode(this, StatusBarHelper.ANDROID_M)
        init()
    }

    private fun init() {
        findViewById()
        //初始化底部导航栏
        initBottomNavigationView()
        initViewPager()
    }

    private fun findViewById() {
        mBottomNavigationView = mViewDataBinding.bottomNavigationView
        mViewPager = mViewDataBinding.viewPager
    }

    //初始化底部导航栏
    private fun initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(
            object : BottomNavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                    mMenuItem = item
                    when (item.itemId) {
                        R.id.mainpage -> {
                            mViewPager.setCurrentItem(0)
                            return true
                        }
                        R.id.classify -> {
                            mViewPager.setCurrentItem(1)
                            return true
                        }
                        R.id.my -> {
                            mViewPager.setCurrentItem(2)
                            return true
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })
    }

    private fun initViewPager() {
        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        mViewPager.adapter = mViewPagerAdapter
        val list = ArrayList<Fragment>()
        list.add(MainFragment())
        list.add(ClassifyFragment())
        list.add(MyFragment())
        mViewPagerAdapter.setList(list)
        mViewPager.offscreenPageLimit = 2
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (mMenuItem != null) {
                    mMenuItem!!.setChecked(false)
                } else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false)
                }
                mMenuItem = mBottomNavigationView.getMenu().getItem(position)
                mMenuItem!!.setChecked(true)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}