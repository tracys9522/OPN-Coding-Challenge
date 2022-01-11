package com.example.opncodingchallenge.http

data class BaseResponse<T>(
    val code: String,
    val msg: String,
    val data: T?
)
