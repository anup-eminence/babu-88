package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
	@field:SerializedName("timeStamp")
	val timeStamp: String?,

	@field:SerializedName("secretKey")
	val secretKey: String?,

	@field:SerializedName("conPass")
	val conPass: String?,

	@field:SerializedName("newPass")
	val newPass: String?,

	@field:SerializedName("oldPass")
	val oldPass: String?
)