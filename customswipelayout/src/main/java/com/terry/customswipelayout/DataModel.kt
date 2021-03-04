package com.terry.customswipelayout

import androidx.fragment.app.Fragment
import java.util.*

class DataModel {

    companion object{
        fun getFragmentList(): List<Fragment>? {
            val list: MutableList<Fragment> = ArrayList<Fragment>()
            list.add(FirstFragment())
            list.add(SecondFragment())
            return list
        }
    }


}