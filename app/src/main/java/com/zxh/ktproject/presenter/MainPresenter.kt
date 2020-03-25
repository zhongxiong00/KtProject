package com.zxh.ktproject.presenter

import androidx.appcompat.app.AppCompatActivity
import com.zxh.ktproject.base.BasePresenter
import com.zxh.ktproject.ui.iview.IMainView

class MainPresenter(activity: AppCompatActivity) : BasePresenter<IMainView>() {
    private val mActivity = activity


    override fun createViewModel() {

    }

}