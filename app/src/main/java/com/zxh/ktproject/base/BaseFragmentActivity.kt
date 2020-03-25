package com.zxh.ktproject.base

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.zxh.ktproject.ui.iview.IBaseView
import com.zxh.ktproject.utils.FragmentHelper

abstract class BaseFragmentActivity<T:BasePresenter<out IBaseView>> : BaseActivity<T>(), IFragmentSwitch {

    protected var mCurrTag: String? = null

    override fun switchFragment(fragment: Fragment) {
        mCurrTag = FragmentHelper.switchFragment(supportFragmentManager, getFragmentViewId(), fragment, mCurrTag)
    }

    override fun replaceFragment(fragment: Fragment) {
        mCurrTag = FragmentHelper.repleaceFragment(supportFragmentManager, getFragmentViewId(), fragment, mCurrTag)
    }

    override fun showToFragment(fragment: Fragment) {
        mCurrTag = FragmentHelper.showToFragment(supportFragmentManager, getFragmentViewId(), fragment)
    }

    override fun reloadRootFragment(fragment: Fragment) {
        mCurrTag = FragmentHelper.reloadRootFragment(supportFragmentManager, getFragmentViewId(), fragment)
    }

    protected fun getCurrFragment(): Fragment? {
        return FragmentHelper.getCurrFragment(supportFragmentManager, getFragmentViewId(), mCurrTag)
    }

    override fun pop(): Boolean {
        val result = FragmentHelper.pop(supportFragmentManager)
        if (result) {
            FragmentHelper.getTopFragment(supportFragmentManager)?.let {
                mCurrTag = FragmentHelper.getTag(it)
            }
        }
        return result
    }

    @IdRes
    abstract fun getFragmentViewId(): Int

    override fun onBackPressed() {
        val currFragment = FragmentHelper.getCurrFragment(supportFragmentManager, getFragmentViewId(), mCurrTag)
    }
}