package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class TransactionPLRequest(
    @SerializedName("userid") val userId: String,
    @SerializedName("start") val start: String,
    @SerializedName("end") val end: String,
    @SerializedName("timeStamp") val timeStamp: String,
    @SerializedName("secretKey") val secretKey: String
)