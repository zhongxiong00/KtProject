package com.zxh.ktproject.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zxh.ktproject.base.BaseViewModel
import com.zxh.ktproject.entity.UserInfoBean
import com.zxh.ktproject.net.ApiManager

class UserViewModel: BaseViewModel() {
    private lateinit var userInfo: MutableLiveData<UserInfoBean>
    fun login(phone:String,smsCode:String){
        ApiManager.getApiDefaultService().login(phone,smsCode)
    }
}