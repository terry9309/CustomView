package com.terry.alihomeview.xxrecyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class FixedDataScrollDisabledRecyclerView : RecyclerView {
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

    private fun init() {
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
        adapter = getBanner()?.let { RecyclerAdapter(context, it) }
    }


    private fun getBanner(): List<String>? {
        val data: MutableList<String> =
            ArrayList()
        data.add("ParentView item 0")
        data.add("ParentView item 1")
        data.add("ParentView item 2")
        return data
    }
}