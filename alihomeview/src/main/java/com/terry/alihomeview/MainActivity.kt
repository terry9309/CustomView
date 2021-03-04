package com.terry.alihomeview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewPager: ViewPager2 = findViewById(R.id.viewPager2)
        val tabLayout: TabLayout = findViewById(R.id.teblayout)

        viewPager.adapter = getPageFragments()?.let { ViewPagerAdapter(this, it) }

        // binding.comboContentView.setOffscreenPageLimit(1);
        val labels = arrayOf("linear", "scroll", "recycler")
        TabLayoutMediator(
            tabLayout,
            viewPager,
            false,
            TabConfigurationStrategy { tab, position -> tab.text = labels[position] }).attach()

    }


    private fun getPageFragments(): List<Fragment>? {
        val data: MutableList<Fragment> = ArrayList()
        data.add(RecyclerViewFragment())
        data.add(RecyclerViewFragment())
        data.add(RecyclerViewFragment())
        return data
    }
}


