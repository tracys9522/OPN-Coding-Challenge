package com.example.opncodingchallenge.business.order.contract

import com.example.opncodingchallenge.base.BaseContract
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.business.store.model.StoreResultModel

interface ProcessOrderContract {

    interface View : BaseContract.BaseView {
        fun requestOrderOnSuccess()
        fun requestOrderOnFailure()
    }

    interface Presenter {
        fun makeOrder(initOrder: MutableList<ProductModel>, address: String)
    }

}