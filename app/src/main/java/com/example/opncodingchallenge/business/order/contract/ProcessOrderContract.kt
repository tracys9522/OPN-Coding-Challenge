package com.example.opncodingchallenge.business.order.contract

import com.example.opncodingchallenge.base.BaseContract
import com.example.opncodingchallenge.business.store.model.StoreResultModel

interface ProcessOrderContract {

    interface View : BaseContract.BaseView {
        fun requestOrderOnSuccess(storeResultModel: StoreResultModel)
        fun requestOrderOnFailure()
    }

    interface Presenter {
        fun makeOrder()
    }

}