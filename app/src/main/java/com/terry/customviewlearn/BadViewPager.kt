package com.terry.customviewlearn

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

import kotlin.math.abs


/**
 *  外部拦截法
 */

class BadViewPager : ViewPager {

    private var mLastX = 0
    private var mLastY = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
       /* val x = ev?.x?.toInt()
        val y  = ev?.y?.toInt()


        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                mLastX = ev.x.toInt()
                mLastY = ev.y.toInt()

            }

            MotionEvent.ACTION_MOVE ->{
                val deltaX = x?.minus(mLastX)
                val deltaY  = y?.minus(mLastY)
                if (abs(deltaX!!) > abs(deltaY!!)){
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
            }
        }*/




        return super.onInterceptTouchEvent(ev)
    }


}