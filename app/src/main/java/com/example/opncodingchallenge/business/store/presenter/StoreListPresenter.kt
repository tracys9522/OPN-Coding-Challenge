package com.example.opncodingchallenge.business.store.presenter

import com.example.opncodingchallenge.base.BasePresenter
import com.example.opncodingchallenge.business.service.StoreService
import com.example.opncodingchallenge.business.store.contract.StoreListContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StoreListPresenter : BasePresenter<StoreListContract.View>(), StoreListContract.Presenter {
    override fun requestInfo() {
        val storeRequest = StoreService.getInstance().requestStoreInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                it
            }, {

            })
//        val itemRequest = StoreService.getInstance().requestProducts()
//
//        Single.zip(storeRequest, itemRequest, { t1, t2 ->
//            StoreResultModel(t1, t2)
//        }).observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                it
//            }, {
//
//            }).also { addToDisposable(it) }

//        StoreService.getInstance().requestStoreInfo()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                mView?.requestInfoOnSuccess()
//            }, {
//
//            })
//            .also { addToDisposable(it) }

    }
}