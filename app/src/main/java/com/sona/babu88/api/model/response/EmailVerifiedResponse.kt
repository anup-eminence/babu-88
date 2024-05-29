package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class EmailVerifiedResponse(
    @SerializedName("type") val type: String,
    @SerializedName("message") val message: String,
    @SerializedName("title") val title: String
)