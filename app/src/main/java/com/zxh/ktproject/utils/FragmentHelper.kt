package com.zxh.ktproject.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
object FragmentHelper {

    /**
     * 切换fragment
     *
     * @param fragmentManger     当前fragmentManager
     * @param viewId             容器id
     * @param fragment           要显示的fragment
     * @return 返回当前显示的tag
     *
     */
    fun switchFragment(
        fragmentManger: FragmentManager, @IdRes viewId: Int,
        fragment: Fragment, currFragmentTag: String? = null
    ): String {
        val currFragment = getCurrFragment(fragmentManger, viewId, currFragmentTag)
        val tag = getTag(fragment)
        val transaction = fragmentManger.beginTransaction()
        currFragment?.let {
            val currTag = getTag(currFragment)
            if (checkFragmentTag(
                    currTag,
                    tag,
                    fragmentManger,
                    fragment
                )
            ) return getTag(it)
            transaction.hide(currFragment)
//            hideFragment(fragmentManger, transaction, viewId, currTag)
        }

        val findFragment = findFragment(fragmentManger, tag)
        if (findFragment == null) {
            transaction.add(viewId, fragment, tag)
        } else {
            fragment.arguments?.let { arguments ->
                findFragment.arguments = arguments
            }
            transaction.show(findFragment)
        }
        transaction.commitAllowingStateLoss()
        return tag
    }

    fun repleaceFragment(
        fragmentManger: FragmentManager, @IdRes viewId: Int,
        fragment: Fragment, currTag: String? = null
    ): String {
        val currFragment = getCurrFragment(fragmentManger, viewId, currTag)
        val tag = getTag(fragment)
        val transaction = fragmentManger.beginTransaction()
        currFragment?.let {
            val currTag = getTag(currFragment)
            if (checkFragmentTag(
                    currTag,
                    tag,
                    fragmentManger,
                    fragment
                )
            ) return getTag(it)
        }
        transaction.replace(viewId, fragment, tag).commitAllowingStateLoss()
        return tag
    }

    /**
     * 如果toFragment在FragmentManager中则清除toFragment以上的fragment,否则直接添加进FragmentManager中
     */
    fun showToFragment(fm: FragmentManager, @IdRes viewId: Int, toFragment: Fragment): String {
        val bt = fm.beginTransaction()
        val toTag = getTag(toFragment)
        val findFragment = findFragment(fm, toTag)
        if (findFragment != null) {
            val fragments = fm.fragments
            val size = fragments.size - 1
            for (index in size downTo 0) {
                val tempFragment = fragments[index]
                if (getTag(tempFragment) != toTag) {
                    bt.remove(tempFragment)
                } else {
                    break
                }
            }
            bt.show(findFragment)
        } else {
            bt.add(viewId, toFragment, toTag)
        }
        bt.commitAllowingStateLoss()
        return toTag
    }

    fun pop(fragmentManger: FragmentManager): Boolean {
        val fragments = fragmentManger.fragments
        val size = fragments.size
        try {
            if (size > 1) {
                val bt = fragmentManger.beginTransaction()
                bt.remove(fragments[size - 1])
                bt.show(fragments[size - 2])
                bt.commitAllowingStateLoss()
            }

        } catch (e: Exception) {
        }
        return size > 1
    }

    fun findFragment(fragmentManger: FragmentManager, tag: String): Fragment? {
        return fragmentManger.findFragmentByTag(tag)
    }

    /** 重新加载fragment
     * 如果fragmentManger包含rootFragment，则清除fragmentManger中所有其他的fragment，
     * 否则清除fragmentManger中的fragment后添加rootFragment
     * */
    fun reloadRootFragment(fragmentManger: FragmentManager, @IdRes viewId: Int, rootFragment: Fragment): String {
        try {
            val bt = fragmentManger.beginTransaction()
            val rootTag = getTag(rootFragment)
            fragmentManger.fragments.let { fragments ->
                fragments.forEach {
                    if (rootTag != getTag(it)) {
                        bt.remove(it)
                    }
                }
            }
            val findFragment = findFragment(fragmentManger, rootTag)
            if (findFragment != null) {
                bt.show(rootFragment)
            } else {
                bt.add(viewId, rootFragment, rootTag)
            }
            bt.commitAllowingStateLoss()
            return rootTag
        } catch (e: Exception) {

        }
        return ""
    }

    fun getTag(fragment: Fragment): String = fragment::class.java.simpleName

    fun getCurrFragment(fragmentManger: FragmentManager, @IdRes viewId: Int, tag: String? = null): Fragment? {
        val fragment = fragmentManger.findFragmentByTag(tag)
        return if (fragment == null) {
            fragmentManger.findFragmentById(viewId) as? Fragment
        } else {
            fragment as? Fragment
        }
    }

    fun getTopFragment(fragmentManger: FragmentManager): Fragment? {
        val fragments = fragmentManger.fragments
        if (fragments.size > 0) {
            return fragments.last()
        }
        return null
    }

    private fun hideFragment(
        fragmentManager: FragmentManager,
        transaction: FragmentTransaction, @IdRes viewId: Int,
        fragmentTag: String
    ) {
        if (fragmentTag.isNotEmpty()) {
            val tagFragment = findFragment(fragmentManager, fragmentTag)
            if (tagFragment != null) {
                transaction.hide(tagFragment)
            }
        } else {
            val findFragment = fragmentManager.findFragmentById(viewId)
            findFragment?.let {
                transaction.hide(it)
            }
        }
    }

    private fun checkFragmentTag(
        currentFragmentTag: String,
        tag: String,
        fragmentManger: FragmentManager,
        fragment: Fragment
    ): Boolean {
        if (currentFragmentTag == tag) {
            val fragmentTag = findFragment(fragmentManger, currentFragmentTag)
            if (fragment.arguments != null) {
                fragmentTag?.arguments = fragment.arguments
            }
            if (fragmentTag != null) {
                return true
            }
        }
        return false
    }

}