package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class GameListRequest(
    @SerializedName("provider")
    val provider: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("page")
    val page: Int,

    @SerializedName("timeStamp")
    val timeStamp: String,

    @SerializedName("secretKey")
    val secretKey: String
)