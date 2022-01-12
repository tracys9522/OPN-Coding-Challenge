package com.example.opncodingchallenge.http

data class BaseResponse<T>(
    val code: Int,
    val msg: String,
    val data: T?
)
