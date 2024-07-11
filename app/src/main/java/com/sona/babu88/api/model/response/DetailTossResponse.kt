package com.sona.babu88.api.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DetailTossResponse(
    val data : TossData,
    val events : TossEvents
)

@Serializable
data class TossEvents(
    val eventId: String,
    val status: Boolean,
    val volumeCheck: Boolean,
    val markets: List<Market>

)

@Serializable
data class Market(
    val marketId: String,
    val marketName: String,
    val status: Boolean,
    val limit: List<Limit>
)

@Serializable
data class Limit(
    val id: String,
    val name: String,
    val baseCurrency: Boolean,
    val preMinStake: Int,
    val preMaxStake: Int,
    val preMaxPL: Int,
    val minStake: Int,
    val maxStake: Int,
    val maxPL: Int,
    val delay: Int,
    val oddsLimit: Int
)

@Serializable
data class TossData(
    val eventid: String,
    val eventname: String,
    val name: String,
    val provider: String,
    val runnersid: Int,
    val runnersname: String,
    val fancyid: String,
    val btype: String,
    val btypeselection: Int,
    val mtype: String,
    val mtypeselection: Int,
    val status: String
)
