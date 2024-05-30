package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class GetBankingChannelListRequest(
    @field:SerializedName("timeStamp")
    val timeStamp: String?,

    @field:SerializedName("websiteId")
    val websiteId: String?,

    @field:SerializedName("method")
    val method: String?,

    @field:SerializedName("secretKey")
    val secretKey: String?
)

data class GetBankingMethodsRequest(
    @field:SerializedName("timeStamp")
    val timeStamp: String?,

    @field:SerializedName("websiteId")
    val websiteId: String?,

    @field:SerializedName("secretKey")
    val secretKey: String?
)