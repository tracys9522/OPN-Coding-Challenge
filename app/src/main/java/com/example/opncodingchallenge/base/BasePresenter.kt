package com.example.opncodingchallenge.base

import io.reactivex.disposables.Disposable
import java.util.*

abstract class BasePresenter<V : BaseContract.BaseView> : BaseContract.BasePresenter {

    private var mView: V? = null
    private var disposableList: MutableList<Disposable> = ArrayList()


    override fun attachView(view: BaseContract.BaseView) {
        mView = view as V
    }

    override fun detachView() {
        if (mView != null) {
            mView = null
        }
    }

    override fun cancelAll() {
        for (disposable in disposableList) {
            disposable.dispose()
        }
    }

    protected fun addToDisposable(disposable: Disposable) {
        disposableList.add(disposable)
    }
}