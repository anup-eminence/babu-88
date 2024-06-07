package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @field:SerializedName("birthday") val birthday: String?,
    @field:SerializedName("comm") val comm: Int?,
    @field:SerializedName("mobile") val mobile: Boolean?,
    @field:SerializedName("sbcomm") val sbComm: Int?,
    @field:SerializedName("ecomm") val eComm: Int?,
    @field:SerializedName("ccomm") val cComm: Int?,
    @field:SerializedName("mcomm") val mComm: Int?,
    @field:SerializedName("mnumb") val mNumb: String?,
    @field:SerializedName("bcomm") val bComm: Int?,
    @field:SerializedName("expo") val expo: Int?,
    @field:SerializedName("bmcomm") val bmComm: Int?,
    @field:SerializedName("fcomm") val fComm: Int?,
    @field:SerializedName("email") val email: Boolean?
)