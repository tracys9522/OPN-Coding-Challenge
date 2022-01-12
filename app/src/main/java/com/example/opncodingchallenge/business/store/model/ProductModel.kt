package com.example.opncodingchallenge.business.store.model

import java.io.Serializable

data class ProductModel(
    var isSelected: Boolean = false,
    var imageDrawable: String,
    var price: String,
    var quantity: Int = 0
) : Serializable