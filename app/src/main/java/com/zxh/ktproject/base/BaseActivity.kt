package com.zxh.ktproject.base

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zxh.ktproject.R
import com.zxh.ktproject.widgets.DefaultDialog

abstract class BaseActivity<T :BasePresenter> : AppCompatActivity() {
    protected lateinit var  presenter:T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        presenter = createPresenter()
        initView()
    }

    abstract fun initView()

    abstract fun layoutId(): Int

    protected abstract fun createPresenter(): T

    /**
     * 显示loading框
     */
    fun Activity.showLoadingDialog(cancelable: Boolean = true): DefaultDialog? {
        val dialog = DefaultDialog(this)
        try {
            dialog.setContentView(R.layout.widget_dialog_loading)
        } catch (e: Exception) {
            return null
        }
        val loading: View = dialog.findViewById(R.id.loading_root)
        loading.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(cancelable)
        dialog.setCanceledOnTouchOutside(false)
        showDialog(dialog)
        return dialog
    }

    fun Activity.showDialog(dialog: Dialog?): Boolean {
        try {
            if (dialog == null || this.isFinishing || dialog.isShowing) {
                return false
            }
            dialog.show()
            return true
        } catch (t: Throwable) {
        }
        return false
    }
}