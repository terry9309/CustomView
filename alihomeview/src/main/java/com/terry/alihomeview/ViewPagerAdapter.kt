package com.terry.alihomeview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter : FragmentStateAdapter{

    private lateinit var data:List<Fragment>

    constructor(
        fragmentActivity: FragmentActivity,
        data: List<Fragment>
    ) : super(fragmentActivity) {
        this.data = data
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return data[position]
    }
}