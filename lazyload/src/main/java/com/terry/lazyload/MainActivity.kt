package com.terry.lazyload

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.terry.lazyload.viewpager1.ViewPager1Activity
import com.terry.lazyload.viewpager2.ViewPager2Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_viewPager.setOnClickListener {startActivity(Intent(this,ViewPager1Activity::class.java))}
        tv_viewPager2.setOnClickListener{startActivity(Intent(this,ViewPager2Activity::class.java))}
    }
}
