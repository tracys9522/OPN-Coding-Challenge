package com.example.opncodingchallenge.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, P : BaseContract.BasePresenter> : AppCompatActivity(),
    BaseContract.BaseView {
    private var mPresenter: P? = null
    private lateinit var binding: VB


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

    override fun onError(e: Throwable?) {
    }

    protected abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter?.cancelAll()
            mPresenter?.detachView()
        }
    }

}