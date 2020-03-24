package com.zxh.ktproject.utils

import android.view.View

/**
 * 防止快速连点
 */
fun View.setBlockingOnClickListener(listener: View.OnClickListener) {
    setOnClickListener(object : BlockingOnClickListener() {
        override fun onValidClick(view: View?) {
            listener.onClick(view)
        }
    })
}

fun View.setBlockingOnClickListener(listener: (view: View) -> Unit) {
    setOnClickListener(object : BlockingOnClickListener() {
        override fun onValidClick(view: View?) {
            view ?: return
            listener(view)
        }
    })
}


abstract class BlockingOnClickListener(
    private val thresholdMs: Long = 1000L
) : View.OnClickListener {
    private var preTimesMill = 0L

    abstract fun onValidClick(view: View?)

    final override fun onClick(view: View?) {
        val prev = preTimesMill
        val curr = System.currentTimeMillis()
        if (isWithinThreshold(curr, prev)) {
            return
        }
        preTimesMill = curr
        onValidClick(view)
    }

    private fun isWithinThreshold(curr: Long, prev: Long) = (curr - prev) <= thresholdMs
}