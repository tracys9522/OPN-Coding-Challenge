package com.example.opncodingchallenge.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.opncodingchallenge.util.LoadingDialog

abstract class BaseActivity<VB : ViewBinding, P : BaseContract.BasePresenter> : AppCompatActivity(),
    BaseContract.BaseView {
    protected var mPresenter: P? = null
    protected lateinit var binding: VB
    protected lateinit var mLoadingDialog: LoadingDialog
    override var isLoading: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getPresenter()
        mPresenter?.attachView(this)

        binding = getViewBinding()
        setContentView(binding.root)

        initView()
        initData()
    }

    protected abstract fun getViewBinding(): VB

    abstract fun getPresenter(): P

    protected abstract fun initView()

    protected abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter?.cancelAll()
            mPresenter?.detachView()
            mPresenter = null
        }
    }

    override fun showLoading() {
        mLoadingDialog = LoadingDialog()
        mLoadingDialog.show(supportFragmentManager, null)
        isLoading = true
    }

    override fun hideLoading() {
        if (mLoadingDialog.isVisible) {
            mLoadingDialog.dismissAllowingStateLoss()
        }
        isLoading = false
    }

    override fun showToastError(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}