package com.zxh.ktproject.utils

import android.util.Log
import androidx.multidex.BuildConfig

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