package com.example.opncodingchallenge.business.store.model

import com.example.opncodingchallenge.bean.ProductInfoBean
import com.example.opncodingchallenge.bean.StoreInfoBean


data class StoreResultModel(
    var storeInfoBean: StoreInfoBean?,
    var productList: List<ProductInfoBean> = mutableListOf()
)
