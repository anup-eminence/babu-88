package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class GetUserMatchDetailResponse(
    @field:SerializedName("gameType") val gameType: String?,
    @field:SerializedName("matchDate") val matchDate: Long?,
    @field:SerializedName("selctionids") val selctionIds: String?,
    @field:SerializedName("bookt3") val bookT3: String?,
    @field:SerializedName("team3") val team3: String?,
    @field:SerializedName("team1") val team1: String?,
    @field:SerializedName("team2") val team2: String?,
    @field:SerializedName("isOdds") val isOdds: Boolean?,
    @field:SerializedName("cupselctionids") val cupselctionIds: String?,
    @field:SerializedName("bookt2") val bookT2: String?,
    @field:SerializedName("isFancy") val isFancy: Boolean?,
    @field:SerializedName("bookheader") val bookHeader: String?,
    @field:SerializedName("bookt1") val bookT1: String?,
    @field:SerializedName("sportid") val sportId: Int?,
    @field:SerializedName("isBook") val isBook: Boolean?,
    @field:SerializedName("isPrem") val isPrem: Boolean?,
    @field:SerializedName("matchname") val matchName: String?,
    @field:SerializedName("id2") val id2: String?,
    @field:SerializedName("id1") val id1: String?,
    @field:SerializedName("tvurl") val tvUrl: String?,
    @field:SerializedName("id3") val id3: String?,
    @field:SerializedName("preMatchMarket") val preMatchMarket: String?,
    @field:SerializedName("isOther") val isOther: Boolean?,
    @field:SerializedName("device") val device: String?
)