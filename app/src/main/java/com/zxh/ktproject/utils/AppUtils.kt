package com.zxh.ktproject.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class AppUtils {
    companion object {
        private val INTERNALTIME = 1500//间隔时间
        private var firstTime = 0L

        fun canBack(): Boolean {
            val secondTime = System.currentTimeMillis()
            if (secondTime - firstTime > INTERNALTIME) {
                firstTime = secondTime
                return true
            } else {
                return false
            }
        }

        /**
         * 隐藏软键盘
         */
        fun hideSoftInput(activity: Activity) {
            (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                activity.window.decorView.windowToken,
                0
            )
        }

        /**
         * 显示软键盘
         * @param view
         */
        fun showSoftInput(view: View) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}