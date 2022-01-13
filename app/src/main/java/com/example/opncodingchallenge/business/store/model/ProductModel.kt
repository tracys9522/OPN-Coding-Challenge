package com.example.opncodingchallenge.business.store.model

import java.io.Serializable

data class ProductModel(
    var isSelected: Boolean = false,
    var imageDrawable: String,
    var price: Double,
    var unitPrice: Double,
    var quantity: Int = 0
) : Serializable