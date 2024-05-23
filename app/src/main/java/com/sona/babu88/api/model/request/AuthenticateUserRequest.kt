package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class AuthenticateUserRequest(
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("passWord")
    val passWord: String?,
    @SerializedName("host")
    val host: String?,
    @SerializedName("ipad")
    val ipad: String?
)
