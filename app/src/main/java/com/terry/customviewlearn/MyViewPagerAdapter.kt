package com.terry.customviewlearn

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.viewpager.widget.PagerAdapter

class MyViewPagerAdapter(context: Context) : PagerAdapter() {

    private lateinit var mContext: Context
    private lateinit var mData: List<Map<String, Int>>

    constructor(context: Context, list: List<Map<String, Int>>) : this(context) {
        this.mContext = context
        this.mData = list
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 6
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View = View.inflate(mContext, R.layout.item_list, null)
        var listView: ListView = view.findViewById(R.id.list)

        listView.adapter = SimpleAdapter(
            container.context, mData, R.layout.item_base,
            arrayOf("key"), intArrayOf(R.id.iv)
        )

        container.addView(view)


        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}