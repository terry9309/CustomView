package com.terry.customviewlearn

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ListView
import kotlin.math.abs


/**
 *  内部拦截法
 */
class MyListView :ListView{

    private var mLastX = 0
    private var mLastY = 0

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        val x = ev?.x?.toInt()
        val y  = ev?.y?.toInt()

        when(ev?.action){

            MotionEvent.ACTION_DOWN ->{
               /* mLastX = ev.x.toInt()
                mLastY = ev.y.toInt()*/
                parent.requestDisallowInterceptTouchEvent(true)

            }

            MotionEvent.ACTION_MOVE ->{
                val deltaX = x!! -mLastX
                val deltaY  = y!! -mLastY
                if (abs(deltaX) > abs(deltaY)){
                   parent.requestDisallowInterceptTouchEvent(false)

                }
            }

            MotionEvent.ACTION_UP -> {
            }
        }


        mLastX = x!!
        mLastY = y!!
        return super.dispatchTouchEvent(ev)



    }
}