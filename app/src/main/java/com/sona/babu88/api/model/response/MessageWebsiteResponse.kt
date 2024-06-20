package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class MessageWebsiteResponse(
    @field:SerializedName("data") val data: List<Item?>?,
    @field:SerializedName("status") val status: String?
)

data class _Id(
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("timestamp") val timestamp: Int?
)

data class Item(
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("website") val website: String?,
    @field:SerializedName("year") val year: String?,
    @field:SerializedName("webParent") val webParent: String?,
    @field:SerializedName("updatedOn") val updatedOn: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("message") val message: String?,
    @field:SerializedName("isLock") val isLock: Boolean?,
    @field:SerializedName("month") val month: String?,
    @field:SerializedName("subType") val subType: String?,
    @field:SerializedName("_id") val id: _Id?,
    @field:SerializedName("messageDate") val messageDate: String?,
    @field:SerializedName("userType") val userType: Int?,
    @field:SerializedName("_class") val _class: String?,
    @field:SerializedName("updateLong") val updateLong: Long?,
    @field:SerializedName("day") val day: String?
)