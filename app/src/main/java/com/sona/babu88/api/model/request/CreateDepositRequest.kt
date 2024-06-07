package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class CreateDepositRequest(
    @field:SerializedName("timeStamp")
    val timeStamp: String?,

    @field:SerializedName("secretKey")
    val secretKey: String?,

    @field:SerializedName("selectedMethod")
    val selectedMethod: String?,

    @field:SerializedName("selectedMode")
    val selectedMode: String?,

    @field:SerializedName("selectedChannel")
    val selectedChannel: String?,

    @field:SerializedName("selectedPromoId")
    val selectedPromoId: Int?,

    @field:SerializedName("selectedAmount")
    val selectedAmount: Int?,

    @field:SerializedName("selectedCat")
    val selectedCat: String?
)