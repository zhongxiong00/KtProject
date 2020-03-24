package com.zxh.ktproject.ui

import com.zxh.ktproject.R
import com.zxh.ktproject.base.BaseActivity
import com.zxh.ktproject.presenter.LoginPresenter

class LoginActivity : BaseActivity<LoginPresenter>() {


    override fun createPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
    }
}