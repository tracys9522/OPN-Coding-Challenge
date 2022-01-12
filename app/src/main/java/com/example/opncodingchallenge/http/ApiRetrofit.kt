package com.example.opncodingchallenge.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


class ApiRetrofit {
    private var retrofit: Retrofit
    private var client: OkHttpClient
    var apiServer: ApiServer

    companion object {
        const val BASE_URL = "https://c8d92d0a-6233-4ef7-a229-5a91deb91ea1.mock.pstmn.io/"
        const val TAG = "ApiRetrofit"

        @Volatile
        private var INSTANCE: ApiRetrofit? = null
        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ApiRetrofit().also { INSTANCE = it }
            }
    }

    private val interceptor = Interceptor { chain ->
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        val mediaType = response.body()?.contentType()
        val content = response.body()?.toString()
        Log.e(TAG, "----------Request Start----------------")
        Log.e(TAG, "| " + request.toString() + request.headers().toString())
        Log.e(TAG, "| Response:$content")
        Log.e(TAG, "----------Request End:" + duration + "ms----------")
        response.newBuilder()
            .body(ResponseBody.create(mediaType, content!!))
            .build()
    }

    init {
        client = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ResponseConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        apiServer = retrofit.create(ApiServer::class.java)
    }

}