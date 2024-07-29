package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class GetBankingMethodsResponse(
    @field:SerializedName("ok")
    val ok: Double?,
    @field:SerializedName("results")
    val results: List<ResultsItem?>?
)

data class ResultsItem(
    @field:SerializedName("isUpay") val isUpay: Boolean?,
    @field:SerializedName("isNagad") val isNagad: Boolean?,
    @field:SerializedName("_id") val id: String?,
    @field:SerializedName("isBkash") val isBkash: Boolean?,
    @field:SerializedName("isRocket") val isRocket: Boolean?
)