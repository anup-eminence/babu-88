package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class UserSCPackResponse(
    @field:SerializedName("UserSCPackResponse") val userSCPackResponse: List<UserSCPackResponseItem?>?
)

data class UserSCPackResponseItem(
    @field:SerializedName("productValue") val productValue: String?,
    @field:SerializedName("productId") val productId: String?,
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("productName") val productName: String?
)