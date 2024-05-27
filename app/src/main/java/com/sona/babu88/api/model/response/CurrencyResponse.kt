package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("awcName") val awcName: String,
    @SerializedName("intName") val intName: String,
    @SerializedName("value") val value: Double,
    @SerializedName("status") val status: Boolean,
    @SerializedName("code") val code: String,
    @SerializedName("symbol") val symbol: String
)
