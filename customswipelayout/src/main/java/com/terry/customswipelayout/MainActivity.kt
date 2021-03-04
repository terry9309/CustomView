package com.terry.customswipelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        custom_vp.adapter = SubVpAdapter(supportFragmentManager,
        DataModel.getFragmentList())
    }
}
