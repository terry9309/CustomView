package com.terry.lazyload.viewpager1.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.terry.lazyload.R
import com.terry.lazyload.viewpager1.FragmentDelegater
import com.terry.lazyload.viewpager1.base.BaseLoadFragment

class MyFragment4 :BaseLoadFragment() {

    private val TAG = "MyFragment"
    private val POSITION = "Position"

    private var tabIndex = 4



    companion object {
        fun newInstance(position: Int): MyFragment4 {
            val bundle = Bundle()
            bundle.putInt("Position",position)
            val fragment = MyFragment4()
            fragment.setFragmentDelegater(
                FragmentDelegater(
                    fragment
                )
            )
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"fragment${tabIndex}"+"onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)


    }

    override fun getLayoutRes():Int {
        return R.layout.fragment_my_loading
    }

    override fun initView(rootView: View) {
        tabIndex = arguments?.getInt(POSITION)!!
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG,tabIndex.toString()+"fragment"+"onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,tabIndex.toString()+"fragment"+"onPause")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,tabIndex.toString()+"fragment"+"onAttach")
    }

    override fun onDetach() {
        super.onDetach()
       Log.d(TAG,tabIndex.toString()+"fragment"+"onDetach")
    }



}

