package com.example.opncodingchallenge.http

import com.example.opncodingchallenge.bean.ProductInfoBean
import com.example.opncodingchallenge.bean.StoreInfoBean
import io.reactivex.Single
import retrofit2.http.GET

interface ApiServer {
    @GET("/storeInfo")
    fun getStoreInfo(): Single<StoreInfoBean>

    @GET("/products")
    fun getStoreProduct(): Single<List<ProductInfoBean>>
}