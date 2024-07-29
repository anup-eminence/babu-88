package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class VipLevelsResponse(
    @field:SerializedName("reqPoints") val reqPoints: Double?,
    @field:SerializedName("vipToCashRatio") val vipToCashRatio: Double?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("fullName") val fullName: String?,
    @field:SerializedName("currency") val currency: String?,
    @field:SerializedName("minWithdraw") val minWithdraw: Double?,
    @field:SerializedName("id") val id: String?
)