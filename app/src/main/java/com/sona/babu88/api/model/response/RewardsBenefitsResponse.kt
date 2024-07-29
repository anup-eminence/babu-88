package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class RewardsBenefitsResponse(
    @field:SerializedName("ace") val ace: Boolean?,
    @field:SerializedName("code") val code: String?,
    @field:SerializedName("mythic") val mythic: Boolean?,
    @field:SerializedName("grandMaster") val grandMaster: Boolean?,
    @field:SerializedName("legend") val legend: Boolean?,
    @field:SerializedName("fullName") val fullName: String?,
    @field:SerializedName("master") val master: Boolean?,
    @field:SerializedName("gold") val gold: Boolean?,
    @field:SerializedName("elite") val elite: Boolean?,
    @field:SerializedName("platinum") val platinum: Boolean?,
    @field:SerializedName("diamond") val diamond: Boolean?,
    @field:SerializedName("currency") val currency: String?,
    @field:SerializedName("conquerer") val conquerer: Boolean?,
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("crown") val crown: Boolean?
)