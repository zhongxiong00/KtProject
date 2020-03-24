package com.zxh.ktproject.widgets

import android.view.Gravity
import android.widget.Toast
import com.zxh.ktproject.App

private fun toast(
    message: CharSequence,
    duration: Int = Toast.LENGTH_SHORT
) {
    try {
        val toast = Toast.makeText(App.getAppContext(), message, duration)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = duration
        toast.setText(message)
        toast.show()
    } catch (e: Exception) {
    }
}


fun toast(str: String) {
    toast(str, Toast.LENGTH_SHORT)
}





