package com.example.opncodingchallenge.http

data class ApiException(val code: Int, val msg: String?) : RuntimeException()