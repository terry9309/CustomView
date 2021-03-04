package com.terry.lazyload.viewpager1

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.terry.lazyload.R
import com.terry.lazyload.viewpager1.fragment.*
import kotlinx.android.synthetic.main.activity_view_pager1.*
import java.util.*

class ViewPager1Activity : AppCompatActivity() {

    lateinit var mViewPager: ViewPager
    lateinit var mBottomNaView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager1)

        val fragmentList: MutableList<Fragment> = ArrayList()

        fragmentList.add(MyFragment1.newInstance(1))
        fragmentList.add(MyFragment2.newInstance())
        fragmentList.add(MyFragment1.newInstance(3))
        fragmentList.add(MyFragment1.newInstance(4))
        fragmentList.add(MyFragment1.newInstance(5))

        mViewPager = viewPager
        mBottomNaView = bottom_na_view

        mBottomNaView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        viewPager.adapter = MyFragmentPagerAdapter(supportFragmentManager, fragmentList)
        viewPager.offscreenPageLimit = 1
        viewPager.addOnPageChangeListener(viewpagerChangeListener)

    }


   private var viewpagerChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i1: Int) {

        }

        override fun onPageSelected(i: Int) {
            var itemId = R.id.fragment_1
            when (i) {
                0 -> itemId = R.id.fragment_1
                1 -> itemId = R.id.fragment_2
                2 -> itemId = R.id.fragment_3
                3 -> itemId = R.id.fragment_4
                4 -> itemId = R.id.fragment_5
            }
            mBottomNaView.selectedItemId = itemId
        }

        override fun onPageScrollStateChanged(i: Int) {}
    }


    // 当点击 tab1 的时候 就会 设置CurrentItem=0，来设置 ViewPager下标操作
    private var onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.fragment_1 -> {
                        mViewPager.setCurrentItem(0, true)
                        return true
                    }
                    R.id.fragment_2 -> {
                        mViewPager.setCurrentItem(1, true)
                        return true
                    }
                    R.id.fragment_3 -> {
                        mViewPager.setCurrentItem(2, true)
                        return true
                    }
                    R.id.fragment_4 -> {
                        mViewPager.setCurrentItem(3, true)
                        return true
                    }
                    R.id.fragment_5 -> {
                        mViewPager.setCurrentItem(4, true)
                        return true
                    }
                }
                return false
            }
        }
}