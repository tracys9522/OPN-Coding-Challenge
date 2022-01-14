package com.example.opncodingchallenge.http

import com.example.opncodingchallenge.business.service.bean.ProductInfoBean
import com.example.opncodingchallenge.business.service.bean.StoreInfoBean
import com.example.opncodingchallenge.business.service.param.OrderParam
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServer {
    @GET("/storeInfo")
    fun getStoreInfo(): Single<StoreInfoBean>

    @GET("/products")
    fun getStoreProduct(): Single<List<ProductInfoBean>>

    @POST("/order")
    fun makeOrder(@Body orderParam: OrderParam): Completable
}