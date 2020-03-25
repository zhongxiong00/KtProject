package com.zxh.ktproject.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zxh.ktproject.base.BasePresenter
import com.zxh.ktproject.ui.iview.ILoginView
import com.zxh.ktproject.viewmodel.UserViewModel

class LoginPresenter(activity: AppCompatActivity) : BasePresenter<ILoginView>() {
    private val mActivity: AppCompatActivity = activity
    private lateinit var userViewModel: UserViewModel
    fun login(activity: AppCompatActivity, phone: String, smsCode: String) {
        userViewModel.userInfo.observe(activity, Observer {


        })
        userViewModel.login(phone, smsCode, activity)
    }

    override fun createViewModel() {
        userViewModel = initViewModel(mActivity)
    }

}