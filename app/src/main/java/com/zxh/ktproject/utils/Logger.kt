package com.zxh.ktproject.utils

import android.util.Log
import com.zxh.ktproject.BuildConfig

class Logger {
    companion object {
        val TAG = "Logger"
        fun error(message: String) {
            if (BuildConfig.DEBUG) {
                Log.e(Logger.TAG, message)
            }
        }
    }

}