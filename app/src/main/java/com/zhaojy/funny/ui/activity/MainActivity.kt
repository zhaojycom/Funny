package com.zhaojy.funny.ui.activity

import android.os.Bundle
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView
import com.zhaojy.funny.R
import com.zhaojy.funny.adapter.ViewPagerAdapter
import com.zhaojy.funny.helper.StatusBarHelper
import com.zhaojy.funny.ui.fragment.ClassifyFragment
import com.zhaojy.funny.ui.fragment.MainFragment
import com.zhaojy.funny.ui.fragment.MyFragment
import java.util.*

class MainActivity : BaseActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        //设置状态栏字体颜色为深色
        StatusBarHelper.statusBarLightMode(this, StatusBarHelper.ANDROID_M)
        init()
    }

    private fun init() {
        findViewById()
        initViewPager()
        //初始化底部导航栏
        initBottomNavigationView()
    }

    private fun findViewById() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        viewPager = findViewById(R.id.viewPager)
    }

    //初始化底部导航栏
    private fun initBottomNavigationView() {
        val size = resources.getDimension(R.dimen.bottom_navigation_text_size)
        bottomNavigationView.setTextActiveSize(size)
        bottomNavigationView.setTextInactiveSize(size)
        bottomNavigationView.disableShadow()
        val titles = arrayOf(
            resources.getString(R.string.main_page),
            resources.getString(R.string.classify),
            resources.getString(R.string.my)
        )
        val icons = intArrayOf(
            R.mipmap.mainpage,
            R.mipmap.classify,
            R.mipmap.my
        )
        val color = resources.getColor(R.color.theme)
        bottomNavigationView.isColoredBackground(false)
        bottomNavigationView.setItemActiveColorWithoutColoredBackground(color)
        //添加item
        for (i in 0 until titles.size) {
            val title = titles.get(i)
            val icon = icons.get(i)
            val item = BottomNavigationItem(
                title, color, icon
            )
            bottomNavigationView.addTab(item)
        }
        bottomNavigationView.setOnBottomNavigationItemClickListener {
            viewPager.setCurrentItem(it)
        }
    }

    private fun initViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        val list = ArrayList<Fragment>()
        list.add(MainFragment())
        list.add(ClassifyFragment())
        list.add(MyFragment())
        viewPagerAdapter.setList(list)
    }
}