package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class PromoFilterResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("productId") val productId: String,
    @SerializedName("productName") val productName: String,
    @SerializedName("productValue") val productValue: String?
)