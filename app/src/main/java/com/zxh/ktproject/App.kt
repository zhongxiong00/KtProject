package com.zxh.ktproject

import android.content.Context
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import me.jessyan.autosize.AutoSize
import me.jessyan.autosize.AutoSizeConfig

class App : MultiDexApplication() {
    companion object {
        private lateinit var mAppContext: Context
        fun getAppContext(): Context = mAppContext
    }

    override fun onCreate() {
        super.onCreate()
        mAppContext = this
        initAutoSize()
    }

    private fun initAutoSize() {
        AutoSizeConfig.getInstance()
            .setLog(BuildConfig.DEBUG)
        AutoSize.initCompatMultiProcess(this)
    }
}