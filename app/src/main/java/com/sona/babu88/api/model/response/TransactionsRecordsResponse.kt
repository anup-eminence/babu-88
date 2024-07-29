package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class TransactionsRecordsResponse(
    @field:SerializedName("data") val data: List<TransactionsDataItem?>?
)

data class TransactionsDataItem(
    @field:SerializedName("createLong") val createLong: Long?,
    @field:SerializedName("statusDate") val statusDate: String?,
    @field:SerializedName("adminUserid") val adminUserid: String?,
    @field:SerializedName("userReqNumber") val userReqNumber: Any?,
    @field:SerializedName("paymentFee") val paymentFee: Double?,
    @field:SerializedName("code") val code: Any?,
    @field:SerializedName("aprrovedBy") val approvedBy: String?,
    @field:SerializedName("bonus") val bonus: Double?,
    @field:SerializedName("channel") val channel: String?,
    @field:SerializedName("statusLong") val statusLong: Long?,
    @field:SerializedName("type") val type: String?,
    @field:SerializedName("userid") val userid: String?,
    @field:SerializedName("branch") val branch: Any?,
    @field:SerializedName("assignedTo") val assignedTo: String?,
    @field:SerializedName("mode") val mode: String?,
    @field:SerializedName("isFirst") val isFirst: Any?,
    @field:SerializedName("promotionName") val promotionName: String?,
    @field:SerializedName("ourTransid") val ourTransId: String?,
    @field:SerializedName("statusDateTime") val statusDateTime: String?,
    @field:SerializedName("completedOnDate") val completedOnDate: String?,
    @field:SerializedName("isAuto") val isAuto: Boolean?,
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("companyMethod") val companyMethod: Any?,
    @field:SerializedName("website") val website: String?,
    @field:SerializedName("amount") val amount: Double?,
    @field:SerializedName("address") val address: String?,
    @field:SerializedName("method") val method: String?,
    @field:SerializedName("transEndTime") val transEndTime: Long?,
    @field:SerializedName("holder") val holder: Any?,
    @field:SerializedName("createdOnDate") val createdOnDate: String?,
    @field:SerializedName("masterUserId") val masterUserId: String?,
    @field:SerializedName("aprrovedIP") val approvedIP: String?,
    @field:SerializedName("createdOnTime") val createdOnTime: String?,
    @field:SerializedName("district") val district: Any?,
    @field:SerializedName("subAdminUserid") val subAdminUserid: String?,
    @field:SerializedName("depositSlip") val depositSlip: Any?,
    @field:SerializedName("category") val category: String?,
    @field:SerializedName("status") val status: Int?,
    @field:SerializedName("promotion") val promotion: String?,
    @field:SerializedName("userTransId") val userTransId: String?,
    @field:SerializedName("completedOnTime") val completedOnTime: String?
)