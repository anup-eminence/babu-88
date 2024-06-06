package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class GetUserSideBarMatchesRequest(
    @field:SerializedName("timeStamp") val timeStamp: String?,
    @field:SerializedName("sportid") val sportId: String?,
    @field:SerializedName("secretKey") val secretKey: String?
)