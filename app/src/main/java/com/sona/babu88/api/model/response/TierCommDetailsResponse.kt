package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class TierCommDetailsResponse(
    @field:SerializedName("agRgst") val agRgst: Double?,
    @field:SerializedName("minTurn") val minTurn: Double?,
    @field:SerializedName("t32Comm") val t32Comm: Double?,
    @field:SerializedName("t33Comm") val t33Comm: Double?,
    @field:SerializedName("t3Comm") val t3Comm: Double?,
    @field:SerializedName("t53Comm") val t53Comm: Double?,
    @field:SerializedName("t31Comm") val t31Comm: Double?,
    @field:SerializedName("mRComm") val mRComm: Double?,
    @field:SerializedName("t52Comm") val t52Comm: Double?,
    @field:SerializedName("agRDpf") val agRDpf: Double?,
    @field:SerializedName("currency") val currency: String?,
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("t43Comm") val t43Comm: Double?,
    @field:SerializedName("t22Comm") val t22Comm: Double?,
    @field:SerializedName("t21Comm") val t21Comm: Double?,
    @field:SerializedName("t51Comm") val t51Comm: Double?,
    @field:SerializedName("t1Comm") val t1Comm: Double?,
    @field:SerializedName("t42Comm") val t42Comm: Double?,
    @field:SerializedName("t23Comm") val t23Comm: Double?,
    @field:SerializedName("rComm") val rComm: Double?,
    @field:SerializedName("t2Comm") val t2Comm: Double?,
    @field:SerializedName("minTurn2") val minTurn2: Double?,
    @field:SerializedName("agRWpf") val agRWpf: Double?,
    @field:SerializedName("agRComm") val agRComm: Double?,
    @field:SerializedName("t41Comm") val t41Comm: Double?,
    @field:SerializedName("minTurn5") val minTurn5: Double?,
    @field:SerializedName("minTurn3") val minTurn3: Double?,
    @field:SerializedName("minTurn4") val minTurn4: Double?
)