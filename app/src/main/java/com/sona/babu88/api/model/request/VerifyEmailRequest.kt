package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class VerifyEmailRequest(
    @SerializedName("email") val email: String,
    @SerializedName("timeStamp") val timeStamp: String,
    @SerializedName("secretKey") val secretKey: String
)
