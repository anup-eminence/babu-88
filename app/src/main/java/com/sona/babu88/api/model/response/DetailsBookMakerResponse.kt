package com.sona.babu88.api.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsBookMakerResponse(
    @SerialName("Type") val type: String?,
    @SerialName("data") val data: List<DataItem1?>?,
    @SerialName("indianTV") val indianTV: String?,
    @SerialName("banglaTV") val banglaTV: String?,
    @SerialName("events") val events: Events1?
)

@Serializable
data class Events1(
    @SerialName("eventId") val eventId: String?,
    @SerialName("volumeCheck") val volumeCheck: Boolean?,
    @SerialName("markets") val markets: List<MarketsItem1?>?,
    @SerialName("status") val status: Boolean?
)

@Serializable
data class LimitItems(
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
data class Bm1Item(
    @SerialName("nat") val nat: String?,
    @SerialName("bs2") val bs2: String?,
    @SerialName("bs1") val bs1: String?,
    @SerialName("bs3") val bs3: String?,
    @SerialName("l1") val l1: String?,
    @SerialName("max") val max: String?,
    @SerialName("utime") val uTime: String?,
    @SerialName("l2") val l2: String?,
    @SerialName("l3") val l3: String?,
    @SerialName("ls2") val ls2: String?,
    @SerialName("remark") val remark: String?,
    @SerialName("mname") val mName: String?,
    @SerialName("ls1") val ls1: String?,
    @SerialName("ls3") val ls3: String?,
    @SerialName("b1") val b1: String?,
    @SerialName("b2") val b2: String?,
    @SerialName("b3") val b3: String?,
    @SerialName("min") val min: String?,
    @SerialName("s") val s: String?,
    @SerialName("remark1") val remark1: String?
)

@Serializable
data class DataItem1(
    @SerialName("bm2") val bm2: List<Bm1Item?>?,
    @SerialName("bm1") val bm1: List<Bm1Item?>?
)

@Serializable
data class MarketsItem1(
    @SerialName("marketName") val marketName: String?,
    @SerialName("limit") val limit: List<LimitItems?>?,
    @SerialName("marketId") val marketId: String?,
    @SerialName("status") val status: Boolean?
)