package com.example.opncodingchallenge.business.service.bean

import java.io.Serializable

data class StoreInfoBean(
    val closingTime: String,
    val name: String,
    val openingTime: String,
    val rating: Double
) : Serializable