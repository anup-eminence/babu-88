package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class ActiveMultiMarketResponse(
    @field:SerializedName("data") val data: List<DataItem?>?
)

data class Ids(
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("timestamp") val timestamp: Int?
)

data class DataItem(
    @field:SerializedName("dateTime") val dateTime: String?,
    @field:SerializedName("l1") val l1: Any?,
    @field:SerializedName("matchName") val matchName: String?,
    @field:SerializedName("matchType") val matchType: String?,
    @field:SerializedName("l2") val l2: Any?,
    @field:SerializedName("l3") val l3: Any?,
    @field:SerializedName("userid") val userid: String?,
    @field:SerializedName("b1") val b1: Any?,
    @field:SerializedName("b2") val b2: Any?,
    @field:SerializedName("b3") val b3: Any?,
    @field:SerializedName("sportId") val sportId: Int?,
    @field:SerializedName("startTime") val startTime: Long?,
    @field:SerializedName("_id") val id: Ids?,
    @field:SerializedName("_class") val _class: String?,
    @field:SerializedName("matchid") val matchId: String?,
    var isPinned: Boolean? = false
)