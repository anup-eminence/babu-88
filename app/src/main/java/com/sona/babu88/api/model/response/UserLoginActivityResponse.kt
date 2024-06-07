package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class UserLoginActivityResponse(
    @field:SerializedName("totalItems") val totalItems: Int?,
    @field:SerializedName("data") val data: String?,
    @field:SerializedName("totalPages") val totalPages: Int?,
    @field:SerializedName("currentPage") val currentPage: Int?,
    @field:SerializedName("status") val status: String?
)