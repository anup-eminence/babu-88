package com.sona.babu88.api.model.response

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class TotalUserAccountLogsResponse(
    @field:SerializedName("totalItems") val totalItems: Int?,
    @field:SerializedName("data") val data: String?,
    @field:SerializedName("totalPages") val totalPages: Int?,
    @field:SerializedName("currentPage") val currentPage: Int?,
    @field:SerializedName("status") val status: String?
) {
    fun getData(): List<AccountLogsDataItem?>? {
        val parser = JsonParser()
        val jsonArray = parser.parse(data).asJsonArray
        val type = object : TypeToken<List<AccountLogsDataItem?>?>() {}.type
        return Gson().fromJson(jsonArray, type)
    }
}

data class AccountLogsDataItem(
    @field:SerializedName("toUser") val toUser: String?,
    @field:SerializedName("isCredit") val isCredit: Boolean?,
    @field:SerializedName("amount") val amount: Double?,
    @field:SerializedName("balance") val balance: Double?,
    @field:SerializedName("fromUser") val fromUser: String?,
    @field:SerializedName("narration") val narration: String?,
    @field:SerializedName("commPlus") val commPlus: Double?,
    @field:SerializedName("remark") val remark: String?,
    @field:SerializedName("_id") val id: AccountLogsId?,
    @field:SerializedName("commMinus") val commMinus: Double?,
    @field:SerializedName("userId") val userId: String?,
    @field:SerializedName("createdOn") val createdOn: String?
)

data class AccountLogsId(
    @field:SerializedName("randomValue1") val randomValue1: Int?,
    @field:SerializedName("randomValue2") val randomValue2: Int?,
    @field:SerializedName("counter") val counter: Int?,
    @field:SerializedName("timestamp") val timestamp: Int?
)