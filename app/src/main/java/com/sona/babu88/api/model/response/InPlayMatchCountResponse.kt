package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class InPlayMatchCountResponse(
    @field:SerializedName("ok") val ok: Any?,
    @field:SerializedName("results") val results: List<ResultsItems?>?
)

data class ResultsItems(
    @field:SerializedName("_id") val id: Int?,
    @field:SerializedName("marketid") val marketId: Int?
)