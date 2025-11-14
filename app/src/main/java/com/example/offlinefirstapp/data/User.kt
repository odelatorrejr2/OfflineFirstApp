package com.example.offlinefirstapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,

    @Embedded
    val address: Address,

    @Embedded(prefix = "company_")
    val company: Company
)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,

    @Embedded(prefix = "geo_")
    val geo: Geo
)

data class Geo(
    val lat: String,
    val lng: String
)