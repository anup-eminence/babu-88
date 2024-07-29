package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class TotalUserAccountLogsRequest(
    @field:SerializedName("timeStamp") val timeStamp: String?,
    @field:SerializedName("secretKey") val secretKey: String?,
    @field:SerializedName("pageNo") val pageNo: Int?,
    @field:SerializedName("type") val type: String?
)