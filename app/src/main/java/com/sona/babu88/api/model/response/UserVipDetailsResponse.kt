package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class UserVipDetailsResponse(
    @field:SerializedName("pointPer") val pointPer: Int?,
    @field:SerializedName("minReq") val minReq: Int?,
    @field:SerializedName("nextLevel") val nextLevel: String?,
    @field:SerializedName("currLevel") val currLevel: String?,
    @field:SerializedName("points") val points: Int?,
    @field:SerializedName("ratio") val ratio: Int?
)