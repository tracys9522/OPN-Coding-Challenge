package com.example.opncodingchallenge.business.service.param

data class OrderParam(
    val delivery_address: String,
    val products: MutableList<Product>
)

data class Product(
    val imageUrl: String,
    val name: String,
    val price: Double
)