package com.example.opncodingchallenge.business.service

import com.example.opncodingchallenge.bean.ProductInfoBean
import com.example.opncodingchallenge.bean.StoreInfoBean
import com.example.opncodingchallenge.http.ApiRetrofit
import io.reactivex.Single

class StoreService : IStoreService {
    companion object {
        @Volatile
        private var INSTANCE: StoreService? = null
        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoreService().also { INSTANCE = it }
            }
    }

    override fun requestStoreInfo(): Single<StoreInfoBean> {
        return ApiRetrofit.getInstance().apiServer.getStoreInfo()
    }

    override fun requestProducts(): Single<List<ProductInfoBean>> {
        return ApiRetrofit.getInstance().apiServer.getStoreProduct()
    }


}