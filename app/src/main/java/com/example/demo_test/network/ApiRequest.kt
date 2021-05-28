package com.example.demo_test.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @Author: Nisikki
 * @Date: 2021/5/12
 * @Describe:
 */
interface ApiRequest {

    @GET("api/v2/banners")
  suspend  fun getBannerByKotlin(): BaseResponse<List<BannerArray>>


    @GET("api/v2/banners")
    fun getBanner(): Observable<BaseResponse<String>>

}