package com.swapnesh.shadimatcher.Retrofit


import com.swapnesh.shadimatcher.model.UserListResponce
import retrofit2.Call
import retrofit2.http.*



interface JfClient {


    @GET("api/")
    fun getList(@Query("results") results : String): Call<UserListResponce>
}