package com.terry.alihomeview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView

class CustomNestScrollView : NestedScrollView {


    private lateinit var contentView: ViewGroup

    private lateinit var topView: View


    private lateinit var flingHelper: FlingHelper

    constructor(context: Context) : super(context) {

        init()
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        init()
    }


    //总滑动距离
    var totalDy: Int = 0

    //判断recyclerView 是否在滑动
    var isStartFling: Boolean = false

    //记录当前y轴滑动的加速度
    var velocityY: Int = 0



    private fun init() {
        flingHelper = FlingHelper(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

                if (isStartFling) {
                    totalDy = 0
                    isStartFling = false
                }
                if (scrollY == 0) {
                    // Log.i(TAG, "TOP SCROLL");
                    // refreshLayout.setEnabled(true);
                }
                if (scrollY == getChildAt(0).measuredHeight - v.measuredHeight) {
                    //  Log.i(TAG, "BOTTOM SCROLL");
                    dispatchChildFling()
                }
                //在RecyclerView fling情况下，记录当前RecyclerView在y轴的偏移
                totalDy += scrollY - oldScrollY
            }
        }
    }

    private fun dispatchChildFling() {
        if (velocityY != 0) {
            val splineFlingDistance: Double = flingHelper.getSplineFlingDistance(velocityY)
            if (splineFlingDistance > totalDy) {
                childFling(
                    flingHelper.getVelocityByDistance(
                        splineFlingDistance - java.lang.Double.valueOf(
                            totalDy.toDouble()
                        )
                    )
                )
            }
        }
        totalDy = 0
        velocityY = 0
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        topView = (getChildAt(0) as ViewGroup).getChildAt(0) as View

        //这里获取的是tablayout + recyclerView 的View 也就是包裹他们的linearlayout
        contentView = (getChildAt(0) as ViewGroup).getChildAt(1) as ViewGroup
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 调整contentView的高度为父容器高度，使之填充布局，避免父容器滚动后出现空白
        var lp: ViewGroup.LayoutParams = contentView.layoutParams
        lp.height = measuredHeight
        contentView.layoutParams = lp
    }


    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        Log.e("onNestedPreScroll", "${topView.measuredHeight}")
        //此处优先滑动头部View  headerView
        var hideTop: Boolean = dy > 0 && scrollY < topView.measuredHeight
        if (hideTop) {
            scrollBy(0, dy)

            //避免同时滑动
            consumed[1] = dy
        }
    }


    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(target, dx, dy, consumed)

    }

    override fun fling(velocityY: Int) {
        super.fling(velocityY)
        if (velocityY <= 0) {
            this.velocityY = 0
        } else {
            isStartFling = true
            this.velocityY = velocityY
        }
    }


    private fun childFling(velY: Int) {
        val childRecyclerView: RecyclerView? = this!!.getChildRecyclerView(contentView)
        childRecyclerView?.fling(0, velY)
    }


    private fun getChildRecyclerView(viewGroup: ViewGroup): RecyclerView? {
        for (i in 0 until viewGroup.childCount) {
            val view = viewGroup.getChildAt(i)
            if (view is RecyclerView && view.javaClass == CustomNestScrollView::class.java) {
                return viewGroup.getChildAt(i) as RecyclerView
            } else if (viewGroup.getChildAt(i) is ViewGroup) {
                val childRecyclerView: ViewGroup? =
                    getChildRecyclerView(viewGroup.getChildAt(i) as ViewGroup)
                if (childRecyclerView is RecyclerView) {
                    return childRecyclerView
                }
            }
            continue
        }
        return null
    }


}