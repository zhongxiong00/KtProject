package com.zxh.ktproject.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zxh.ktproject.R
import com.zxh.ktproject.ui.iview.IBaseView
import com.zxh.ktproject.widgets.DefaultDialog
import com.zxh.ktproject.widgets.toast

abstract class BaseActivity<P : BasePresenter<out IBaseView>> : AppCompatActivity(),
    IBaseView {
    protected lateinit var presenter: P
    private lateinit var loadingDialog: DefaultDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        presenter = createPresenter()
        initView()
    }

    abstract fun initView()

    abstract fun layoutId(): Int

    protected abstract fun createPresenter(): P

    /**
     * 显示loading框
     */
    override fun showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = DefaultDialog(this)
            loadingDialog.setContentView(R.layout.widget_dialog_loading)
            val loading: View = loadingDialog.findViewById(R.id.loading_root)
            loading.setOnClickListener {
                loadingDialog.dismiss()
            }
            loadingDialog.setCancelable(false)
            loadingDialog.setCanceledOnTouchOutside(false)
        }
        showDialog(loadingDialog)
    }

    override fun dissmissLoadingDialog() {
        loadingDialog?.let {
            if (loadingDialog.isShowing) {
                loadingDialog.dismiss()
            }
        }
    }

    fun showDialog(dialog: Dialog?): Boolean {
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

    override fun showToastMsg(msg: String) {
        toast(msg)
    }
}