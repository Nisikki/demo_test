package com.example.demo_test.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author: Nisikki
 * @Date: 2021/5/12
 * @Describe:
 * */
class BasenNetWork {
    companion object {
//        const val BASE_HOST = "https://zhuanlan.zhihu.com/"
        const val BASE_HOST = "https://gank.io/"

        private var mInstance: BasenNetWork? = null

        fun getInstance(): BasenNetWork {
            return mInstance ?: synchronized(this) {
                mInstance ?: BasenNetWork().also {
                    mInstance = it
                }
            }
        }
    }


    var apiRequest: ApiRequest

    init {


        val builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = builder
            .addInterceptor(httpLoggingInterceptor)
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//           .dns()     //代理相关
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_HOST)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            //不引入会报异常：/StandaloneCoroutine: retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall@ce9a91
            .build()

        apiRequest = retrofit.create(ApiRequest::class.java)
    }
}