package com.example.opncodingchallenge.base


interface BaseContract {
    interface BasePresenter {
        fun attachView(view: BaseView)
        fun detachView()
        fun cancelAll()
    }

    interface BaseView {
        var isLoading: Boolean
        fun showLoading()
        fun hideLoading()
        fun showToastError(msg: String)
        fun onError(e: Throwable?)
    }

}