package com.terry.lazyload.viewpager1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class FragmentDelegater : Fragment {

    private var mFragment: Fragment? = null



    constructor(fragment: Fragment?){
        mFragment = fragment
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dumpLifeCycle("onAttach: " + mFragment.hashCode())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dumpLifeCycle("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater!!, container, savedInstanceState)
        dumpLifeCycle("onCreateView")
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        dumpLifeCycle("onViewCreated")
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dumpLifeCycle("onActivityCreated")
    }

    override fun onViewStateRestored(@Nullable savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
//        dumpLifeCycle("onViewStateRestored");
    }

    override fun onStart() {
        super.onStart()
        dumpLifeCycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        dumpLifeCycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        dumpLifeCycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        dumpLifeCycle("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dumpLifeCycle("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        dumpLifeCycle("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        dumpLifeCycle("onDetach")
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        Log.i(
            "Derry",
            mFragment!!::class.java.name
                .toString() + " -> setUserVisibleHint isVisibleToUser: " + isVisibleToUser + " ============="
        )
    }

    /**
     * 第一次进来不会触发
     * 跳转到下一个页面的时候会触发：true
     * 在回来的时候会触发：false
     * 返回到上一级的时候 不会促发
     *
     * @param hidden
     */
    override fun onHiddenChanged(hidden: Boolean) {
        Log.i(
            "Derry",
            mFragment!!::class.java.simpleName
                .toString() + " -> onHiddenChanged hidden: " + hidden + " ***************"
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        Log.i("Derry", "requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data);
    }

    override fun onInflate(activity: Activity, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(activity!!, attrs!!, savedInstanceState)
//        dumpLifeCycle("onInflate");
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState!!)
//        dumpLifeCycle("onSaveInstanceState");
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig!!)
//        dumpLifeCycle("onConfigurationChanged");
    }

    fun dumpLifeCycle(method: String) {
        Log.i(
            "terry",
            "name: " + mFragment!!::class.java.getSimpleName().toString() + " -> " + method
        )
    }
}