package com.example.opncodingchallenge.http

import com.google.gson.Gson
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

class GsonResponseBodyConverter<T>(private val gson: Gson, private val type: Type) :
    Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T {
        val response = value.string()
        val httpResult = gson.fromJson(response, Response::class.java)
        if (httpResult.code() == 200) {
            return gson.fromJson(response, type)
        } else {
            val errorResponse = gson.fromJson(response, BaseResponse::class.java)
            throw ApiException(errorResponse.code, errorResponse.msg)
        }
    }
}
