package com.example.marsapplication

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface WeatherApis {

    @POST("current.json")
    fun getPhotos(@Header("key") key: String) : Call<Any>

    @GET("api/users/{userId}")
    fun getUserInfo(@Path("userId") userId: Int): Call<MainActivity.UserInfoData>

    @GET("api/users")
    fun getUserInfo2(@Query("userId") userId: Int): Call<Any>


}
