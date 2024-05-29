package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("liability") val liability: Double,
    @SerializedName("balance") val balance: Double,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("mobile") val mobile: Boolean,
    @SerializedName("email") val email: Boolean
)
