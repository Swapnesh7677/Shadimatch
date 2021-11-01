package com.swapnesh.shadimatcher.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey



data class UserListResponce( var info: Info, var results: List<Result>) {

    data class Info(
        var page: Int,
        var results: Int,
        var seed: String,
        var version: String
    )

    data class Result(

        var cell: String,
        var dob: Dob,
        var email: String,
        var gender: String,
        var id: Id,
        var location: Location,
        var login: Login,
        var name: Name,
        var nat: String,
        var phone: String,
        var picture: Picture,
        var registered: Registered
    )  {


        data class Dob(
            var age: Int,
            var date: String
        )

        data class Id(
            var name: String,
            var value: String
        )

        data class Location(
            var city: String,
            var coordinates: Coordinates,
            var country: String,
            var postcode: Any,
            var state: String,
            var street: Street,
            var timezone: Timezone
        ) {
            data class Coordinates(
                var latitude: String,
                var longitude: String
            )

            data class Street(
                var name: String,
                var number: Int
            )

            data class Timezone(
                var description: String,
                var offset: String
            )
        }

        data class Login(
            var md5: String,
            var password: String,
            var salt: String,
            var sha1: String,
            var sha256: String,
            var username: String,
            var uuid: String
        )

        data class Name(
            var first: String,
            var last: String,
            var title: String
        )

        data class Picture(
            var large: String,
            var medium: String,
            var thumbnail: String
        )

        data class Registered(
            var age: Int,
            var date: String
        )
    }
}