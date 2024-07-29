package com.sona.babu88.api.model.response

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class UsersPromotionResponse(
    @field:SerializedName("totalItems") val totalItems: Int?,
    @field:SerializedName("data") val data: String?,
    @field:SerializedName("totalPages") val totalPages: Int?,
    @field:SerializedName("currentPage") val currentPage: Int?,
    @field:SerializedName("status") val status: String?
) {
    fun getData(): List<TurnoverDataItem?>? {
        val parser = JsonParser()
        val jsonArray = parser.parse(data).asJsonArray
        val type = object : TypeToken<List<TurnoverDataItem?>?>() {}.type
        return Gson().fromJson(jsonArray, type)
    }
}

data class TurnoverDataItem(
    @field:SerializedName("payoutAmount") val payoutAmount: String?,
    @field:SerializedName("expireOn") val expireOn: String?,
    @field:SerializedName("expireLong") val expireLong: Long?,
    @field:SerializedName("payOutBase") val payOutBase: Int?,
    @field:SerializedName("bonus") val bonus: Double?,
    @field:SerializedName("selectionName") val selectionName: String?,
    @field:SerializedName("settleAmount") val settleAmount: Double?,
    @field:SerializedName("casinoGames") val casinoGames: String?,
    @field:SerializedName("casinoCompany") val casinoCompany: String?,
    @field:SerializedName("closedOn") val closedOn: String?,
    @field:SerializedName("ptype") val pType: Int?,
    @field:SerializedName("turnOver") val turnOver: Double?,
    @field:SerializedName("createdOn") val createdOn: String?,
    @field:SerializedName("createdLong") val createdLong: Long?,
    @field:SerializedName("promotionId") val promotionId: String?,
    @field:SerializedName("turnOverType") val turnOverType: Int?,
    @field:SerializedName("promotionName") val promotionName: String?,
    @field:SerializedName("expiryType") val expiryType: Int?,
    @field:SerializedName("endsOnLong") val endsOnLong: Long?,
    @field:SerializedName("bonusLong") val bonusLong: Long?,
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("closedLong") val closedLong: Long?,
    @field:SerializedName("maxPayout") val maxPayout: Any?,
    @field:SerializedName("sourceIds") val sourceIds: List<String?>?,
    @field:SerializedName("endsOn") val endsOn: String?,
    @field:SerializedName("eventId") val eventId: String?,
    @field:SerializedName("adminUserId") val adminUserId: String?,
    @field:SerializedName("totalBonus") val totalBonus: Double?,
    @field:SerializedName("promotionType") val promotionType: List<Int?>?,
    @field:SerializedName("requireTurnOver") val requireTurnOver: Double?,
    @field:SerializedName("expiryValue") val expiryValue: Int?,
    @field:SerializedName("selectionId") val selectionId: String?,
    @field:SerializedName("minBaseReq") val minBaseReq: Double?,
    @field:SerializedName("promoType") val promoType: String?,
    @field:SerializedName("maxBaseReq") val maxBaseReq: Double?,
    @field:SerializedName("casinoType") val casinoType: String?,
    @field:SerializedName("subadminUserId") val subAdminUserId: String?,
    @field:SerializedName("userId") val userId: String?,
    @field:SerializedName("totalPl") val totalPl: Double?,
    @field:SerializedName("masterUserId") val masterUserId: String?,
    @field:SerializedName("transactionAmt") val transactionAmt: Double?,
    @field:SerializedName("sportId") val sportId: Int?,
    @field:SerializedName("commId") val commId: String?,
    @field:SerializedName("casinoProvider") val casinoProvider: String?,
    @field:SerializedName("poster") val poster: String?,
    @field:SerializedName("status") val status: Int?
)