package com.terry.lazyload.viewpager1.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.terry.lazyload.R
import com.terry.lazyload.viewpager1.FragmentDelegater
import com.terry.lazyload.viewpager1.TestActivity
import com.terry.lazyload.viewpager1.base.BaseLoadFragment

class MyFragment1 :BaseLoadFragment() {

    private val TAG = "MyFragment"
    private val POSITION = "Position"

    private var tabIndex = 1
    private var con: CountDownTimer? = null
    private var tvLoading:TextView? = null
    private var iv:ImageView?= null
    private var tvToOtherActivity:TextView? = null


    companion object {
        fun newInstance(position: Int): MyFragment1 {
            val bundle = Bundle()
            bundle.putInt("Position",position)
            val fragment = MyFragment1()
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

    override fun getLayoutRes():Int {
        return R.layout.fragment_my_loading
    }

    override fun initView(rootView: View) {
        tabIndex = arguments?.getInt(POSITION)!!
        iv = rootView.findViewById(R.id.iv_content)
        tvLoading = rootView.findViewById(R.id.tv_loading)
        tvToOtherActivity = rootView.findViewById(R.id.tv_to_other_activity)

        tvToOtherActivity?.setOnClickListener {startActivity(Intent( activity,TestActivity::class.java))}
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


    override fun onFragmentLoadStop() {
        super.onFragmentLoadStop()
        tabIndex = arguments!!.getInt(POSITION)
        handler.removeMessages(10)
        if (con != null) {
            con!!.cancel()
        }
        Log.d(TAG, "$tabIndex fragment 暂停一切操作 pause")
        //对UI操作的代表
        //对UI操作的代表
        tvLoading?.text = "李元霸"
    }

    override fun onFragmentLoad() {
        super.onFragmentLoad()
        Log.d(TAG, "$tabIndex fragment 真正更新界面")
        getData()
    }



    private fun getData() {
        con = object : CountDownTimer(1000, 100) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                handler.sendEmptyMessage(0)
            }
        }
        (con as CountDownTimer).start()
    }


    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            tvLoading?.visibility = View.GONE
            val id: Int
            id = when (tabIndex) {
                1 -> R.drawable.a
                2 -> R.drawable.b
                3 -> R.drawable.c
                4 -> R.drawable.d
                else -> R.drawable.a
            }
            iv?.setImageResource(id)
            iv?.scaleType = ImageView.ScaleType.FIT_XY
            iv?.visibility = View.VISIBLE

            if (tabIndex ==5){
                iv?.visibility =View.GONE
                tvToOtherActivity?.visibility = View.VISIBLE
            }


            Log.d(TAG, "$tabIndex handleMessage: ")
            //模拟耗时工作
            try {
                Thread.sleep(40)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (con != null) {
            con!!.cancel()
        }
    }


}


