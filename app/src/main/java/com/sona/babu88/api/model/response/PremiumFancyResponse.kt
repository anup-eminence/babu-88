package com.sona.babu88.api.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PremiumFancyResponse(
    @SerialName("Type") val type: String?,
    @SerialName("data") val data: Data?,
    @SerialName("events") val events: EventsPRM?
)

@Serializable
data class Data(
    @SerialName("producerUpdateDate") val producerUpdateDate: Long?,
    @SerialName("highlightMarketId") val highlightMarketId: String?,
    @SerialName("settingUpdateDate") val settingUpdateDate: Long?,
    @SerialName("systemSuspend") val systemSuspend: Int?,
    @SerialName("isElectronic") val isElectronic: Int?,
    @SerialName("sportsBookMarket") val sportsBookMarket: List<SportsBookMarketItem?>?,
    @SerialName("eventUpdateDate") val eventUpdateDate: Long?,
    @SerialName("mappingStatus") val mappingStatus: Int?,
    @SerialName("score") val score: String?,
    @SerialName("min") val min: Int?,
    @SerialName("bookMode") val bookMode: String?,
    @SerialName("eventName") val eventName: String?,
    @SerialName("mappingUpdateDate") val mappingUpdateDate: Long?,
    @SerialName("rebateRatio") val rebateRatio: Int?,
    @SerialName("live") val live: Int?,
    @SerialName("eventId") val eventId: Int?,
    @SerialName("closeSite") val closeSite: String?,
    @SerialName("max") val max: Int?,
    @SerialName("eventType") val eventType: Int?,
    @SerialName("version") val version: Long?,
    @SerialName("producerStatus") val producerStatus: Int?,
    @SerialName("eventStatus") val eventStatus: Int?,
    @SerialName("gameProductUpdateDate") val gameProductUpdateDate: Long?,
    @SerialName("apisite") val apiSite: Int?,
    @SerialName("gameProductStatus") val gameProductStatus: Int?
)

@Serializable
data class LimitItemPRM(
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
data class SportsBookSelectionItem(
    @SerialName("eventId") val eventId: Int?,
    @SerialName("updateDate") val updateDate: Long?,
    @SerialName("selectionName") val selectionName: String?,
    @SerialName("handicap") val handicap: Int?,
    @SerialName("apiSite") val apiSite: Int?,
    @SerialName("eventType") val eventType: Int?,
    @SerialName("isActive") val isActive: Int?,
    @SerialName("apiSiteSelectionId") val apiSiteSelectionId: String?,
    @SerialName("betfairEventId") val betfairEventId: Int?,
    @SerialName("marketId") val marketId: String?,
    @SerialName("odds") val odds: Double?,
    @SerialName("id") val id: Int?,
    @SerialName("oddsUpdateDate") val oddsUpdateDate: Long?
)

@Serializable
data class SportsBookMarketItem(
    @SerialName("eventId") val eventId: Int?,
    @SerialName("closeSite") val closeSite: String?,
    @SerialName("updateDate") val updateDate: Long?,
    @SerialName("apiSiteSpecifier") val apiSiteSpecifier: String?,
    @SerialName("numberOfWinner") val numberOfWinner: Int?,
    @SerialName("max") val max: Int?,
    @SerialName("selectionTs") val selectionTs: Long?,
    @SerialName("eventType") val eventType: Int?,
    @SerialName("betfairEventId") val betfairEventId: Int?,
    @SerialName("apiSiteStatus") val apiSiteStatus: String?,
    @SerialName("apiSiteMarketId") val apiSiteMarketId: String?,
    @SerialName("isExpand") val isExpand: Int?,
    @SerialName("marketName") val marketName: String?,
    @SerialName("min") val min: Int?,
    @SerialName("bookMode") val bookMode: String?,
    @SerialName("marketStatus") val marketStatus: Int?,
    @SerialName("apisite") val apisite: Int?,
    @SerialName("id") val id: String?,
    @SerialName("numberOfActiveRunners") val numberOfActiveRunners: Int?,
    @SerialName("sportsBookSelection") val sportsBookSelection: List<SportsBookSelectionItem?>?
)

@Serializable
data class EventsPRM(
    @SerialName("eventId") val eventId: String?,
    @SerialName("volumeCheck") val volumeCheck: Boolean?,
    @SerialName("markets") val markets: List<MarketsItemPRM?>?,
    @SerialName("status") val status: Boolean?
)

@Serializable
data class MarketsItemPRM(
    @SerialName("marketName") val marketName: String?,
    @SerialName("limit") val limit: List<LimitItemPRM?>?,
    @SerialName("marketId") val marketId: String?,
    @SerialName("status") val status: Boolean?
)