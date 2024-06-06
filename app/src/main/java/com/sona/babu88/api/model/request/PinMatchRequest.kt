package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class PinMatchRequest(
    @SerializedName("matchId") val matchId: String?,
    @SerializedName("timeStamp") val timeStamp: String?,
    @SerializedName("secretKey") val secretKey: String?,
)