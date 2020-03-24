package com.zxh.ktproject.base

import androidx.fragment.app.Fragment

interface IFragmentSwitch {
    fun switchFragment(fragment: Fragment)

    fun replaceFragment(fragment: Fragment)

    fun showToFragment(fragment: Fragment)

    fun reloadRootFragment(fragment: Fragment)

    fun pop(): Boolean
}