package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class SpecialGameListRequest(
    @SerializedName("timeStamp")
    val timeStamp: String,

    @SerializedName("secretKey")
    val secretKey: String
)