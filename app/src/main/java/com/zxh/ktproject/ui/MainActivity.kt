package com.zxh.ktproject.ui

import com.zxh.ktproject.R
import com.zxh.ktproject.base.BaseActivity
import com.zxh.ktproject.presenter.MainPresenter
import com.zxh.ktproject.ui.iview.IMainView
import com.zxh.ktproject.utils.AppUtils
import com.zxh.ktproject.widgets.toast

class MainActivity : BaseActivity<MainPresenter>(),IMainView {


    override fun createPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun initView() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun onBackPressed() {
        if (AppUtils.canBack()) {
            toast("再按一次退出程序")
        } else {
            super.onBackPressed()
        }
    }
}
