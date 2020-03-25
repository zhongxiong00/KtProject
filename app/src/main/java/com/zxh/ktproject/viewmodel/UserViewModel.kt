package com.zxh.ktproject.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zxh.ktproject.base.BaseViewModel
import com.zxh.ktproject.entity.UserInfoBean
import com.zxh.ktproject.net.ApiManager

class UserViewModel : BaseViewModel() {
    public lateinit var userInfo: MutableLiveData<UserInfoBean>
    fun login(
        phone: String,
        smsCode: String,
        activity: AppCompatActivity
    ) {
        ApiManager.getApiDefaultService().login(phone, smsCode).observe(activity, Observer {

        })
    }
}