package com.sona.babu88.api.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FancyResponse(
    @SerialName("Type2") val type2: String?,
    @SerialName("diamond") val diamond: List<DiamondItem?>?,
    @SerialName("events") val events: EventsFancy?,
    @SerialName("Type1") val type1: String?
)

@Serializable
data class LimitItemFancy(
    @SerialName("preMinStake") val preMinStake: Int?,
    @SerialName("preMaxPL") val preMaxPL: Int?,
    @SerialName("minStake") val minStake: Int?,
    @SerialName("delay") val delay: Int?,
    @SerialName("name") val name: String?,
    @SerialName("oddsLimit") val oddsLimit: Int?,
    @SerialName("id") val id: String?,
    @SerialName("maxPL") val maxPL: Int?,
    @SerialName("baseCurrency") val baseCurrency: Boolean?,
    @SerialName("preMaxStake") val preMaxStake: Int?,
    @SerialName("maxStake") val maxStake: Int?
)

@Serializable
data class EventsFancy(
    @SerialName("eventId") val eventId: String?,
    @SerialName("volumeCheck") val volumeCheck: Boolean?,
    @SerialName("markets") val markets: List<MarketsItemFancy?>?,
    @SerialName("status") val status: Boolean?
)

@Serializable
data class MarketsItemFancy(
    @SerialName("marketName") val marketName: String?,
    @SerialName("limit") val limit: List<LimitItemFancy?>?,
    @SerialName("marketId") val marketId: String?,
    @SerialName("status") val status: Boolean?
)

@Serializable
data class DiamondItem(
    @SerialName("nat") val nat: String?,
    @SerialName("bs1") val bs1: String?,
    @SerialName("gtype") val gType: String?,
    @SerialName("l1") val l1: String?,
    @SerialName("utime") val uTime: String?,
    @SerialName("max") val max: String?,
    @SerialName("selectionId") val selectionId: String?,
    @SerialName("mid") val mid: String?,
    @SerialName("remark") val remark: String?,
    @SerialName("ls1") val ls1: String?,
    @SerialName("sid") val sid: String?,
    @SerialName("b1") val b1: String?,
    @SerialName("min") val min: String?,
    @SerialName("ballsess") val ballsEss: String?,
    @SerialName("gstatus") val gStatus: String?,
    @SerialName("srno") val srNo: String?
)