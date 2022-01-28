package com.example.opncodingchallenge.business.order.presenter

import com.example.opncodingchallenge.base.BasePresenter
import com.example.opncodingchallenge.business.order.contract.ProcessOrderContract
import com.example.opncodingchallenge.business.service.param.OrderParam
import com.example.opncodingchallenge.business.service.param.Product
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.http.ApiRetrofit
import com.example.opncodingchallenge.util.rxTransform

class ProcessOrderPresenter : BasePresenter<ProcessOrderContract.View>(),
    ProcessOrderContract.Presenter {
    override fun makeOrder(initOrder: MutableList<ProductModel>, address: String) {
        mView?.apply {
            if (!isLoading) showLoading()
        }
        val products = mutableListOf<Product>()
        initOrder.onEach {
            products.add(
                Product(
                    imageUrl = it.imageDrawable,
                    price = it.price,
                    name = it.name
                )
            )
        }

        ApiRetrofit.getInstance().apiServer.makeOrder(
            OrderParam(
                delivery_address = address,
                products = products
            )
        ).rxTransform()
            .subscribe({
                mView?.apply {
                    if (isLoading) {
                        hideLoading()
                    }
                }
                mView?.requestOrderOnSuccess()
            }, {
                mView?.apply {
                    if (isLoading) {
                        hideLoading()
                    }
                }
                mView?.onError(it)
            })
            .also { addToDisposable(it) }

    }

}
