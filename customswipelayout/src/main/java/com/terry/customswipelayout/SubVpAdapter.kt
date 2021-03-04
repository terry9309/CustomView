package com.terry.customswipelayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SubVpAdapter :FragmentPagerAdapter {

    private var list: List<Fragment>? = null

    constructor(fm: FragmentManager, list: List<Fragment>?) : super(fm) {
        this.list = list
    }


    override fun getItem(position: Int): Fragment {
       return list?.get(position)!!
    }

    override fun getCount(): Int {
       return list?.size!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "第一个" else "第二个"
    }
}