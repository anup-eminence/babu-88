package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest(
    @SerializedName("userId") val userId: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("passWord") val passWord: String,
    @SerializedName("mobileNumber") val mobileNumber: String,
    @SerializedName("email") val email: String,
    @SerializedName("refCode") val refCode: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("timeStamp") val timeStamp: String,
    @SerializedName("secretKey") val secretKey: String
)