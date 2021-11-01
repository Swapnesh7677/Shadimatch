package com.triologic.jewel_cliq.repo

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.swapnesh.shadimatcher.R
import com.swapnesh.shadimatcher.Retrofit.JfClient
import com.swapnesh.shadimatcher.Retrofit.JfServer
import com.swapnesh.shadimatcher.Utils.Logger
import com.swapnesh.shadimatcher.model.UserListResponce

import com.swapnesh.shadimatcher.utils.Constants
import com.swapnesh.shadimatcher.utils.Constants.Companion.API_URL_TAG


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

object MainRepo {
    private var jfClient: JfClient = JfServer.getRetrofitInstance().create(JfClient::class.java)

    fun getlist(ml_list: MutableLiveData<UserListResponce>, s: String) {


        val apiName = "list"


        val req: Call<UserListResponce> = jfClient.getList("10")
        Log.e("TAG", "URL"   + req.request().url)
        req.request().url
        //Logger.logApi(API_URL_TAG, apiName, VRC(req.request().url.toString(), params).getRequestedUrl())

        req.enqueue(object : Callback<UserListResponce> {
            override fun onResponse(call: Call<UserListResponce>, response: Response<UserListResponce>) {
                if (response.isSuccessful) {
                    ml_list.value = response.body()
                } else {
                    Log.e("TAG", "Oops! Something went wrong")

                    ml_list.setValue(null)
                }
            }

            override fun onFailure(call: Call<UserListResponce>, t: Throwable) {
                  t.printStackTrace()
                Logger.logApi(Constants.API_ERROR_TAG, apiName, t.localizedMessage)
                Log.e("TAG", "Oops! Something went wrong" )
                ml_list.value = null
            }

        })


    }


}