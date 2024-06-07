package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class PromotionListResponse(
    @SerializedName("totalItems") val totalItems: Int?,
    @SerializedName("data") val data: List<Promotion>?
)

data class Promotion(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("poster") val poster: String?,
    @SerializedName("pstartDate") val pstartDate: String?,
    @SerializedName("pdrawDate") val pdrawDate: String?,
    @SerializedName("websiteId") val websiteId: String?,
    @SerializedName("promotionType") val promotionType: List<Int>?,
    @SerializedName("pstartDateLong") val pstartDateLong: Long?,
    @SerializedName("pdrawDateLong") val pdrawDateLong: Long?,
    @SerializedName("promotionId") val promotionId: String?,
    @SerializedName("promoType") val promoType: String?,
    @SerializedName("status") val status: Boolean?,
    @SerializedName("ptype") val ptype: Int?,
    @SerializedName("description") val description: String? // Using String to handle the HTML content
)