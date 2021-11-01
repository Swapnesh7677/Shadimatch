package com.swapnesh.shadimatcher.utils


import com.swapnesh.shadimatcher.BuildConfig



class Constants {

    companion object {
        const val BSER_URL = "https://randomuser.me/"


        //tags
        const val API_URL_TAG = "API_URL"
        const val API_ERROR_TAG = "API_ERROR"
        const val API_RESPONSE_TAG = "API_RESPONSE"

        //WS Msg
        const val SERVER_ERROR = "Oops! Server Error"
        const val API_NULL_RESPONSE_ERROR = "Oops! Something went wrong"
        const val API_INVALID_RESPONSE_ERROR = "Oops! Got Invalid Response"


        //debug
        const val CUSTOM_DEBUG_TAG = "custom_debug"
        const val CUSTOM_DEBUG_CAT_TAG = "custom_cat_debug"
        var showResponse = BuildConfig.DEBUG
        //AWS





    }

}