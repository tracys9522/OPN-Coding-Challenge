package com.example.opncodingchallenge.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

abstract class BasePresenter<V : BaseContract.BaseView> : BaseContract.BasePresenter {

    protected var mView: V? = null
    private var mCompositeDisposable: CompositeDisposable? = null


    override fun attachView(view: BaseContract.BaseView) {
        mView = view as V
    }

    override fun detachView() {
        if (mView != null) {
            mView = null
        }
    }

    override fun cancelAll() {
        mCompositeDisposable?.dispose()
    }

    protected fun addToDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(disposable)
    }
}