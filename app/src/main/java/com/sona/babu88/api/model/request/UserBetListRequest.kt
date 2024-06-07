package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class UserBetListRequest(
    @field:SerializedName("timeStamp") val timeStamp: String?,
    @field:SerializedName("sportId") val sportId: String?,
    @field:SerializedName("secretKey") val secretKey: String?,
    @field:SerializedName("endDate") val endDate: String?,
    @field:SerializedName("pageNo") val pageNo: Int?,
    @field:SerializedName("pageSize") val pageSize: Int?,
    @field:SerializedName("startDate") val startDate: String?,
    @field:SerializedName("status") val status: String?
)

data class SportsPLRequest(
    @field:SerializedName("timeStamp") val timeStamp: String?,
    @field:SerializedName("secretKey") val secretKey: String?,
    @field:SerializedName("start") val start: String?,
    @field:SerializedName("end") val end: String?
)

data class MatchResultListRequest(
    @field:SerializedName("timeStamp") val timeStamp: String?,
    @field:SerializedName("sportId") val sportId: String?,
    @field:SerializedName("secretKey") val secretKey: String?,
    @field:SerializedName("endDate") val endDate: String?,
    @field:SerializedName("startDate") val startDate: String?
)


data class UserLoginActivityRequest(
    @field:SerializedName("timeStamp") val timeStamp: String?,
    @field:SerializedName("secretKey") val secretKey: String?,
    @field:SerializedName("pageNo") val pageNo: Int?,
    @field:SerializedName("pageSize") val pageSize: Int?
)