package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserData(
    @SerializedName("login")
    val login: Boolean?,
    @SerializedName("user")
    val user: User?
)

data class User(
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("myBalance")
    val myBalance: Double?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("lastWithdraw")
    val lastWithdraw: Double?,
    @SerializedName("isActive")
    val isActive: Boolean?,
    @SerializedName("userName")
    val userName: String?,
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("createdLong")
    val createdLong: Double?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("isLock")
    val isLock: Boolean?,
    @SerializedName("number")
    val number: Long?,
    @SerializedName("ispasswordChanged")
    val ispasswordChanged: Boolean?,
    @SerializedName("loginStamp")
    val loginStamp: Double?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("userType")
    val userType: Double?,
    @SerializedName("refCode")
    val refCode: String?,
    @SerializedName("lastDeposit")
    val lastDeposit: Double?,
    @SerializedName("email")
    val email: String?
)
