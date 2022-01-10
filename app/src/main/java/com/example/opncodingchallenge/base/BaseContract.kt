package com.example.opncodingchallenge.base


interface BaseContract {
    interface BasePresenter {
        fun attachView(view: BaseView)
        fun detachView()
        fun cancelAll()
    }

    interface BaseView {
        fun onError(e: Throwable?)
    }

}