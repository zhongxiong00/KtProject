package com.zxh.ktproject.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BasePresenter {
    init {
        createViewModel()
    }
    inline fun <reified T : ViewModel> initViewModel(activity: AppCompatActivity): T {
        return ViewModelProvider(activity)[T::class.java]
    }

    inline fun <reified T : ViewModel> initViewModel(fragment: Fragment): T {
        return ViewModelProvider(fragment)[T::class.java]
    }

    abstract fun createViewModel()

}