package com.example.opncodingchallenge.business.store.presenter

import com.example.opncodingchallenge.base.BasePresenter
import com.example.opncodingchallenge.business.store.contract.StoreListContract
import com.example.opncodingchallenge.business.store.model.StoreResultModel
import com.example.opncodingchallenge.http.ApiRetrofit
import com.example.opncodingchallenge.util.rxTransform
import io.reactivex.Single

class StoreListPresenter : BasePresenter<StoreListContract.View>(), StoreListContract.Presenter {
    override fun requestInfo() {
        mView?.apply {
            if (!isLoading) showLoading()
        }
        val storeRequest = ApiRetrofit.getInstance().apiServer.getStoreInfo()
        val itemRequest = ApiRetrofit.getInstance().apiServer.getStoreProduct()

        Single.zip(storeRequest, itemRequest) { t1, t2 ->
            StoreResultModel(t1, t2)
        }.rxTransform()
            .subscribe(
                {
                    mView?.apply {
                        if (isLoading) {
                            hideLoading()
                        }
                    }
                    mView?.requestInfoOnSuccess(it)
                }, {
                    mView?.apply {
                        if (isLoading) {
                            hideLoading()
                        }
                    }
                    mView?.onError(it)
                }
            )
            .also { addToDisposable(it) }

    }
}