package com.swapnesh.shadimatcher.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userdata")
data class UserRoom (
    @ColumnInfo(name = "title")
    var title: String="",

    @ColumnInfo(name = "first")
    var first: String="",

    @ColumnInfo(name = "last")
    var last: String="",

    @ColumnInfo(name = "city")
    var city: String="",

    @ColumnInfo(name = "age")
    var age: String="",

    @ColumnInfo(name = "large")
    var large: String="",
    @ColumnInfo(name = "medium")
    var medium: String="",
    @ColumnInfo(name = "thumbnail")
    var thumbnail: String="",
    @ColumnInfo(name = "accept")
    var accept: String=""


){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}