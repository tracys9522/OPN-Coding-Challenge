package com.example.opncodingchallenge.business.store.presenter

import com.example.opncodingchallenge.base.BasePresenter
import com.example.opncodingchallenge.business.store.contract.StoreListContract
import com.example.opncodingchallenge.business.store.model.StoreResultModel
import com.example.opncodingchallenge.http.ApiRetrofit
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StoreListPresenter : BasePresenter<StoreListContract.View>(), StoreListContract.Presenter {
    override fun requestInfo() {
        val storeRequest = ApiRetrofit.getInstance().apiServer.getStoreInfo()
        val itemRequest = ApiRetrofit.getInstance().apiServer.getStoreProduct()

        Single.zip(storeRequest, itemRequest, { t1, t2 ->
            StoreResultModel(t1, t2)
        }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    mView?.requestInfoOnSuccess(it)
                }, {
                    mView?.onError(it)
                }
            )
            .also { addToDisposable(it) }

    }
}