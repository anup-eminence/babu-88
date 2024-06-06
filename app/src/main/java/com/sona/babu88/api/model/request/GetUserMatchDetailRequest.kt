package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class GetUserMatchDetailRequest(
    @field:SerializedName("timeStamp") val timeStamp: String?,
    @field:SerializedName("eventId") val eventId: String?,
    @field:SerializedName("secretKey") val secretKey: String?
)