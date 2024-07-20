package com.sona.babu88.api.model.response

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class PromotionListResponse(
    @SerializedName("totalItems") val totalItems: Int?,
    @SerializedName("data") val data: String?,
    @SerializedName("log") val log: Boolean?,
    @SerializedName("totalPages") val totalPages: Int?,
    @SerializedName("currentPage") val currentPage: Int?,
    @SerializedName("status") val status: String?
) {
    fun getData(): List<Promotion> {
        val parser = JsonParser()
        val jsonArray = parser.parse(data).asJsonArray
        val type = object : TypeToken<List<Promotion>>() {}.type
        return Gson().fromJson(jsonArray, type)
    }
}

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
    @SerializedName("desciption") val description: String?,
    @SerializedName("payoutBase") val payoutBase: Int?
)

data class DepositPromotionListResponse(
    @SerializedName("data") val data: List<Promotion>?,
    @SerializedName("status") val status: String?
)