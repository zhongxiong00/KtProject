package com.zxh.ktproject.widgets

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.zxh.ktproject.R

open class DefaultDialog : Dialog {

    protected val WRAP = 1f
    protected val MATCH = 2f

    constructor(context: Context) : this(context, R.style.App_Dialog)

    constructor(context: Context, style: Int) : super(context, style)


    protected fun setDisplaySize(widthPercent: Float, heightPercent: Float) {
        val window = window
        val layoutParams = window!!.attributes
        val wm = window.windowManager
        val d = wm.defaultDisplay // 获取屏幕宽、高用
        if (widthPercent == WRAP) {
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        } else if (widthPercent == MATCH) {
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        } else {
            layoutParams.width = (d.width * widthPercent).toInt()
        }
        if (heightPercent == WRAP) {
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        } else if (heightPercent == MATCH) {
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        } else {
            layoutParams.height = (d.height * heightPercent).toInt()
        }

        window.attributes = layoutParams
    }

    /**
     * 显示对话框的时候，如果正好 Activity 关闭了，那么会抛出 Window Leaked 异常导致崩溃
     */
    override fun show() {
        try {
            super.show()
        } catch (e: Exception) {
        }

    }

    override fun dismiss() {
        if (isShowing) {
            try {
                super.dismiss()
            } catch (e: Exception) {
            }

        }
    }
}