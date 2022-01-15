package com.example.opncodingchallenge.business.store.contract

import com.example.opncodingchallenge.base.BaseContract
import com.example.opncodingchallenge.business.store.model.StoreResultModel

interface StoreListContract {

    interface View : BaseContract.BaseView {
        fun requestInfoOnSuccess(storeResultModel: StoreResultModel)
    }

    interface Presenter {
        /**
         * @return get store info and products
         */
        fun requestInfo()
    }

}