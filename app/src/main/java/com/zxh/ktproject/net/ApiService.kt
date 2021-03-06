package com.zxh.ktproject.net

import androidx.lifecycle.LiveData
import com.zxh.ktproject.entity.RespData
import com.zxh.ktproject.entity.UserInfoBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("")
    fun login(@Field("") userName: String, @Field("") smsCode: String): LiveData<RespData<UserInfoBean>>
}