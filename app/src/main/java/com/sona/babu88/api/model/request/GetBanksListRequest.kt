package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class GetBanksListRequest(
    @field:SerializedName("timeStamp")
    val timeStamp: String?,

    @field:SerializedName("secretKey")
    val secretKey: String?,

    @field:SerializedName("currency")
    val currency: String?
)