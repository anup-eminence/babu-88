package com.sona.babu88.api.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsResponse(
    @SerialName("Type") val type: String?,
    @SerialName("data") val data: List<DataItems?>?,
    @SerialName("indianTV") val indianTV: String?,
    @SerialName("banglaTV") val banglaTV: String?,
    @SerialName("events") val events: Events?
)

@Serializable
data class AvailableToLayItem(
    @SerialName("size") val size: Double?,
    @SerialName("price") val price: Double?
)

@Serializable
data class Events(
    @SerialName("eventId") val eventId: String?,
    @SerialName("volumeCheck") val volumeCheck: Boolean?,
    @SerialName("markets") val markets: List<MarketsItem?>?,
    @SerialName("status") val status: Boolean?
)

@Serializable
data class LimitItem(
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
data class RunnersItem(
    @SerialName("ex") val ex: Ex?,
    @SerialName("selectionId") val selectionId: Int?
)

@Serializable
data class Ex(
    @SerialName("availableToBack") val availableToBack: List<AvailableToBackItem?>?,
    @SerialName("availableToLay") val availableToLay: List<AvailableToLayItem?>?
)

@Serializable
data class MarketsItem(
    @SerialName("marketName") val marketName: String?,
    @SerialName("limit") val limit: List<LimitItem?>?,
    @SerialName("marketId") val marketId: String?,
    @SerialName("status") val status: Boolean?
)

@Serializable
data class DataItems(
    @SerialName("totalMatched") val totalMatched: Double?,
    @SerialName("lastMatchTime") val lastMatchTime: String?,
    @SerialName("runners") val runners: List<RunnersItem?>?,
    @SerialName("marketId") val marketId: String?,
    @SerialName("status") val status: String?
)

@Serializable
data class AvailableToBackItem(
    @SerialName("size") val size: Double?,
    @SerialName("price") val price: Double?
)