package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class GetBankingChannelListResponse(
	@field:SerializedName("address")
	val address: String?,

	@field:SerializedName("method")
	val method: String?,

	@field:SerializedName("channel")
	val channel: String?,

	@field:SerializedName("_id")
	val id: Id?,

	@field:SerializedName("category")
	val category: Category?
)

data class Id(
	@field:SerializedName("date")
	val date: String?,

	@field:SerializedName("timestamp")
	val timestamp: Int?
)

data class Category(
	@field:SerializedName("date")
	val date: String?,

	@field:SerializedName("timestamp")
	val timestamp: Int?
)