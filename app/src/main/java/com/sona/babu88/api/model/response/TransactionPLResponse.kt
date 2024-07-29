package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class TransactionPLResponse(
    @field:SerializedName("ok") val ok: Double?,
    @field:SerializedName("results") val results: List<TransactionResultsItem?>?
)

data class TransactionId(
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("sportId") val sportId: Int?,
    @field:SerializedName("sourceType") val sourceType: String?
)

data class TransactionResultsItem(
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("sportId") val sportId: Int?,
    @field:SerializedName("resultDateTime") val resultDateTime: Long?,
    @field:SerializedName("sourceType") val sourceType: String?,
    @field:SerializedName("commM") val commM: Double?,
    @field:SerializedName("sportName") val sportName: String?,
    @field:SerializedName("_id") val id: TransactionId?,
    @field:SerializedName("turnOver") val turnOver: Double?,
    @field:SerializedName("userid") val userid: String?,
    @field:SerializedName("pnl") val pnl: Double?
)