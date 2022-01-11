package com.example.opncodingchallenge.business.service

import com.example.opncodingchallenge.bean.ProductInfoBean
import com.example.opncodingchallenge.bean.StoreInfoBean
import io.reactivex.Single

interface IStoreService {
    fun requestStoreInfo(): Single<StoreInfoBean>
    fun requestProducts(): Single<List<ProductInfoBean>>
}