package com.zxh.ktproject.presenter

import androidx.appcompat.app.AppCompatActivity
import com.zxh.ktproject.base.BasePresenter
import com.zxh.ktproject.viewmodel.UserViewModel

class LoginPresenter(activity: AppCompatActivity) : BasePresenter() {
    private val mActivity: AppCompatActivity = activity
    private lateinit var userViewModel: UserViewModel

    override fun createViewModel() {
        userViewModel = initViewModel(mActivity)
    }

}