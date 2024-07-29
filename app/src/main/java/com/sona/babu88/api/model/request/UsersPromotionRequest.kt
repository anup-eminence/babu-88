package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class UsersPromotionRequest(
	@field:SerializedName("timeStamp") val timeStamp: String?,
	@field:SerializedName("secretKey") val secretKey: String?,
	@field:SerializedName("pageNo") val pageNo: Int?,
	@field:SerializedName("status") val status: Int?
)