package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class GetUserSideBarMatchesResponse(
    @field:SerializedName("ok") val ok: Any?,
    @field:SerializedName("results") val results: List<ResultItem?>?
)

data class ResultItem(
    @field:SerializedName("openTimestamp") val openTimestamp: Long?,
    @field:SerializedName("l1") val l1: Any?,
    @field:SerializedName("l2") val l2: Any?,
    @field:SerializedName("matchType") val matchType: String?,
    @field:SerializedName("l3") val l3: Any?,
    @field:SerializedName("seriesid") val seriesId: String?,
    @field:SerializedName("marketid") val marketId: String?,
    @field:SerializedName("b1") val b1: Any?,
    @field:SerializedName("b2") val b2: Any?,
    @field:SerializedName("b3") val b3: Any?,
    @field:SerializedName("seriesname") val seriesName: String?,
    @field:SerializedName("sportid") val sportId: Int?,
    @field:SerializedName("matchname") val matchName: String?,
    @field:SerializedName("_id") val id: String?,
    @field:SerializedName("time") val time: String?,
    @field:SerializedName("day") val day: String?,
    var isPinned: Boolean? = false
)