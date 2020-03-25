package com.zxh.ktproject.ui

import com.zxh.ktproject.R
import com.zxh.ktproject.base.BaseActivity
import com.zxh.ktproject.presenter.LoginPresenter
import com.zxh.ktproject.ui.iview.ILoginView

class LoginActivity : BaseActivity<LoginPresenter>(), ILoginView {


    override fun createPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
    }

    override fun loginSuccess() {

    }
}