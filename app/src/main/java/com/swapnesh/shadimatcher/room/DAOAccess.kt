package com.triologic.jewel_cliq.room


import androidx.room.*
import com.swapnesh.shadimatcher.model.UserRoom


@Dao
interface DAOAccess {
    @Insert
    fun InsertData(userRoom: UserRoom)


    @Query("SELECT * FROM userdata")
    fun getAll(): List<UserRoom>


    @Query("UPDATE userdata SET accept =:accept WHERE first = :firstname")
    fun update(firstname: String, accept: String)


}