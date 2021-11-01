package com.triologic.jewel_cliq.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.swapnesh.shadimatcher.model.UserRoom
import com.triologic.jewel_cliq.room.ListDatabase


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ListRepository {

    companion object {

        var listDatabase: ListDatabase? = null



        fun initializeDB(context: Context): ListDatabase {
            return ListDatabase.getDataseClient(context)
        }


        fun insertData(
            context: Context,
            title: String,
            first: String,
            last: String,
            city: String,
            age: String,
            large: String,
            medium: String,
            thumbnail: String,
            acccept: String, ) {

            listDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val userDetails = UserRoom(title, first,last,city,age,large,medium,thumbnail,acccept)
                listDatabase!!.UserDao().InsertData(userDetails)
            }

        }




    }
}