package com.example.opncodingchallenge.business.store.contract

import com.example.opncodingchallenge.base.BaseContract

interface StoreListContract {

    interface View : BaseContract.BaseView {
        fun requestInfoOnSuccess()
        fun getInfoOnFailure()
    }

    interface Presenter {
        /**
         * @return get store info and products
         */
        fun requestInfo()
    }

}