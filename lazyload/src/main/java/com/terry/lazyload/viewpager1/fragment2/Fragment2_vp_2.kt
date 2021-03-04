package com.terry.lazyload.viewpager1.fragment2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.terry.lazyload.R
import com.terry.lazyload.viewpager1.FragmentDelegater
import com.terry.lazyload.viewpager1.base.BaseLoadFragment

class Fragment2_vp_2:BaseLoadFragment() {


    private val TAG = "Fragment2_vp_1"

    companion object {
        fun newInstance(): Fragment? {
            val fragment = Fragment2_vp_2()
            fragment.setFragmentDelegater(FragmentDelegater(fragment))
            return fragment
        }


    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_vp_2
    }


    override fun initView(rootView: View) {

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
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onFragmentLoadStop() {
        super.onFragmentLoadStop()


        Log.d(TAG, "onFragmentLoadStop" + " 停止一切更新")
    }

    override fun onFragmentLoad() {
        super.onFragmentLoad()
        Log.d(TAG, "onFragmentLoad" + " 真正更新数据")
    }
}