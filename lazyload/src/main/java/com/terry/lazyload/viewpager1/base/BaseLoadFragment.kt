package com.terry.lazyload.viewpager1.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.terry.lazyload.viewpager1.FragmentDelegater

abstract class BaseLoadFragment : Fragment() {

    var mFragmentDelegater: FragmentDelegater? = null

    var rootView: View? = null

    var isViewCreate = false

    var isVisibleToUserLast = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false)
        }
        isViewCreate = true


        initView(rootView!!)

        if (userVisibleHint) {
            // 手动来分发下
            userVisibleHint = true
        }

        return rootView
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        E("setUserVisibleHint")
        if (isViewCreate) {

            if (isVisibleToUser  && !isVisibleToUserLast ) {
                dispatchUserVisibleHint(true)
            } else if (!isVisibleToUser && isVisibleToUserLast) {

                dispatchUserVisibleHint(false)
            }
        }

    }

    private fun dispatchUserVisibleHint(b: Boolean) {
        //改变标志位的状态
        isVisibleToUserLast = b

        if (b && isParentInvisible()){
            return
         }
        if (b) {
            onFragmentLoad()
            dispatchChildVisibleState(true)
        } else {
            onFragmentLoadStop()
            dispatchChildVisibleState(true)
        }

    }


    private fun isParentInvisible(): Boolean {
        val parentFragment = parentFragment
        if (parentFragment is BaseLoadFragment) {
            val fragment: BaseLoadFragment? = parentFragment as BaseLoadFragment?
            return !fragment?.isVisibleToUserLast!!
        }
        return false
    }


    // TODO 为了解决第二个问题，T1 到 T2 T2里面嵌套的ViewPager的Fragment默认不会分发执行
    //  解决：需要手动的分发执行嵌套Fragment里面的
    protected open fun dispatchChildVisibleState(state: Boolean) {
        val fragmentManager: FragmentManager = childFragmentManager
        val fragments: List<Fragment> = fragmentManager.getFragments()
        if (fragments != null) {
            for (fragment in fragments) { // 循环遍历 嵌套里面的 子 Fragment 来分发事件操作
                if (fragment is BaseLoadFragment && !fragment.isHidden && fragment.userVisibleHint) {
                    (fragment as BaseLoadFragment).dispatchUserVisibleHint(state)
                }
            }
        }
    }


    protected abstract fun getLayoutRes(): Int


    protected abstract fun initView(rootView: View)


    // -->>>停止网络数据请求
    open fun onFragmentLoadStop() {
        E("onFragmentLoadStop")
    }

    // -->>>加载网络数据请求
    open fun onFragmentLoad() {
        E("onFragmentLoad")
    }

    override fun onResume() {
        super.onResume()
        E("onResume")
        if (userVisibleHint && !isVisibleToUserLast){
            dispatchUserVisibleHint(true)
        }

    }

    override fun onPause() {
        super.onPause()
        E("onPause")
        if (userVisibleHint && isVisibleToUserLast){
            dispatchUserVisibleHint(false)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        E("onDestroyView")
    }


    // 工具相关而已
    open fun setFragmentDelegater(fragmentDelegater: FragmentDelegater) {
        mFragmentDelegater = fragmentDelegater
    }

    open fun E(string: String) {
        if (mFragmentDelegater != null) {
            mFragmentDelegater?.dumpLifeCycle(string)
        }
    }


}