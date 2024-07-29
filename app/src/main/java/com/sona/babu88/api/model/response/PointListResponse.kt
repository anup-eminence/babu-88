package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class PointListResponse(
    @field:SerializedName("vipPoint") val vipPoint: Double?,
    @field:SerializedName("betAmount") val betAmount: Double?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("fullName") val fullName: String?,
    @field:SerializedName("currency") val currency: String?,
    @field:SerializedName("id") val id: String?
)