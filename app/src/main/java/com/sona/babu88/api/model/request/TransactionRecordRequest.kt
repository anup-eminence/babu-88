package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class TransactionRecordRequest(
    @SerializedName("userid") val userId: String,
    @SerializedName("start") val start: String,
    @SerializedName("end") val end: String,
    @SerializedName("type") val type: String,
    @SerializedName("status") val status: String,
    @SerializedName("timeStamp") val timeStamp: String,
    @SerializedName("secretKey") val secretKey: String
)
