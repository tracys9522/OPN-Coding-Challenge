package com.example.opncodingchallenge.http

import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ResponseConverterFactory(private var gson: Gson?) : Converter.Factory() {

    companion object {
        fun create(): ResponseConverterFactory = create(Gson())
        private fun create(gson: Gson?) = ResponseConverterFactory(gson)
    }

    init {
        if (gson == null) throw NullPointerException("gson == null")
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return GsonResponseBodyConverter<Any?>(gson!!, type)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        return GsonResponseBodyConverter(gson!!, type)
    }
}