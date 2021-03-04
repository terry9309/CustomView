package com.terry.lazyload.viewpager1.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.terry.lazyload.R
import com.terry.lazyload.viewpager1.FragmentDelegater
import com.terry.lazyload.viewpager1.base.BaseLoadFragment

import com.terry.lazyload.viewpager1.fragment2.Fragment2_vp_1
import com.terry.lazyload.viewpager1.fragment2.Fragment2_vp_2
import com.terry.lazyload.viewpager1.fragment2.Fragment2_vp_3
import com.terry.lazyload.viewpager1.fragment2.Fragment2_vp_4
import java.util.*

class MyFragment2 :BaseLoadFragment() {

    private val TAG = "Fragment2"

    private var viewPager //对应的viewPager
            : ViewPager? = null
    private var fragmentsList //view数组
            : ArrayList<Fragment>? = null

    companion object {
        fun newInstance(): Fragment {
            val fragment = MyFragment2()
            fragment.setFragmentDelegater(FragmentDelegater(fragment))
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_2_vp
    }

    override fun initView(view: View) {
        viewPager = view.findViewById(R.id.viewpager01)
        fragmentsList = ArrayList<Fragment>()

        // 又加载四个 子Fragment
        Fragment2_vp_1.newInstance()?.let { fragmentsList!!.add(it) }
        Fragment2_vp_2.newInstance()?.let { fragmentsList!!.add(it) }
        Fragment2_vp_3.newInstance()?.let { fragmentsList!!.add(it) }
        Fragment2_vp_4.newInstance()?.let { fragmentsList!!.add(it) }
        /**
         * 实例化一个PagerAdapter
         * 必须重写的两个方法
         * getCount
         * getItem
         */
        val pagerAdapter: PagerAdapter = object : FragmentPagerAdapter(childFragmentManager) {
           override fun getItem(i: Int): Fragment {
                return fragmentsList!![i]
            }

            override fun getCount(): Int {
                return fragmentsList!!.size
            }


        }
        viewPager?.setAdapter(pagerAdapter)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: " + "Fragment2")
    }

    override fun onPause() {
        super.onPause()
    }


}

