package com.zxh.ktproject.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zxh.ktproject.ui.iview.IBaseView


abstract class BasePresenter<V : IBaseView> {
    private lateinit var view: V

    init {
        createViewModel()
    }

    fun attachView(v: V) {
        this.view = v
    }

    protected inline fun <reified T : ViewModel> initViewModel(activity: AppCompatActivity): T {
        return ViewModelProvider(activity)[T::class.java]
    }

    protected inline fun <reified T : ViewModel> initViewModel(fragment: Fragment): T {
        return ViewModelProvider(fragment)[T::class.java]
    }

    fun getView(): V {
        return view
    }

    abstract fun createViewModel()
}