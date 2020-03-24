package com.zxh.ktproject

import com.zxh.ktproject.base.BaseActivity
import com.zxh.ktproject.presenter.MainPresenter
import com.zxh.ktproject.utils.AppUtils
import com.zxh.ktproject.utils.Logger
import com.zxh.ktproject.widgets.toast

class MainActivity : BaseActivity<MainPresenter>() {


    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun onBackPressed() {
        if (AppUtils.canBack()) {
            Logger.error("-----------------1")
            toast("再按一次退出程序")
        } else {
            Logger.error("-----------------2")
            super.onBackPressed()
        }
    }
}
