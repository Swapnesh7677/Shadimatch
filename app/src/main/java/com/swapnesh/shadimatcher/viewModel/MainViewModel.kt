package com.swapnesh.shadimatcher.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.swapnesh.shadimatcher.model.UserListResponce


import com.triologic.jewel_cliq.repo.MainRepo


class MainViewModel(application: Application) : BaseViewModel(application) {
    val mllist: LiveData<UserListResponce> = MutableLiveData()

    fun list() {
        MainRepo.getlist(
            mllist as MutableLiveData<UserListResponce>,
            "10")
    }


}