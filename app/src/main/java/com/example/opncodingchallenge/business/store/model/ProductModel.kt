package com.example.opncodingchallenge.business.store.model

data class ProductModel(
    var isSelected: Boolean = false,
    var imageDrawable: String,
    var price: String,
    var quantity: Int = 0
)