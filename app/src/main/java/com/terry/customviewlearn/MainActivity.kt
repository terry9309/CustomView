package com.terry.customviewlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_viewpager.*

class MainActivity : AppCompatActivity() {


    private val iv = intArrayOf(
        R.mipmap.iv_0,
        R.mipmap.iv_1,
        R.mipmap.iv_2,
        R.mipmap.iv_3,
        R.mipmap.iv_4,
        R.mipmap.iv_5,
        R.mipmap.iv_6,
        R.mipmap.iv_7,
        R.mipmap.iv_8
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        val strings = ArrayList<HashMap<String,Int>>()
        var map :HashMap<String,Int>

        for (index in 0..30) {
            map = HashMap()
            map["key"] = iv[index % 9]
            strings.add(map)
        }

        viewpager.adapter = MyViewPagerAdapter(this,strings)

    }
}