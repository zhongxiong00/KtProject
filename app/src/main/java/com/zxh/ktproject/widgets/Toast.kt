package com.zxh.ktproject.widgets

import android.widget.Toast
import com.zxh.ktproject.App

private fun toast(
    message: CharSequence,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(App.getAppContext(), message, duration).show()
}


fun toast(str: String) {
    toast(str, Toast.LENGTH_SHORT)
}





