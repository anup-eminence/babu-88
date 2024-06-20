package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class MessageWebsiteResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Id(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null
)

data class DataItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("year")
	val year: String? = null,

	@field:SerializedName("webParent")
	val webParent: String? = null,

	@field:SerializedName("updatedOn")
	val updatedOn: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isLock")
	val isLock: Boolean? = null,

	@field:SerializedName("month")
	val month: String? = null,

	@field:SerializedName("subType")
	val subType: String? = null,

	@field:SerializedName("_id")
	val id: Id? = null,

	@field:SerializedName("messageDate")
	val messageDate: String? = null,

	@field:SerializedName("userType")
	val userType: Int? = null,

	@field:SerializedName("_class")
	val class: String? = null,

	@field:SerializedName("updateLong")
	val updateLong: Long? = null,

	@field:SerializedName("day")
	val day: String? = null
)
