package com.sona.babu88.api.model.response

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class GetUserMatchDetailResponse(
    @field:SerializedName("gameType") val gameType: String?,
    @field:SerializedName("matchDate") val matchDate: Long?,
    @field:SerializedName("selctionids") val selctionIds: String?,
    val selectionIdsList : List<SelectionIds>?,
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
    val preMatchMarketList: List<PreMatchMarket>?,
    @field:SerializedName("isOther") val isOther: Boolean?,
    @field:SerializedName("device") val device: String?
){
    fun getSelectionIdsListItem(): List<SelectionIds> {
        val listType = object : TypeToken<List<SelectionIds>>() {}.type
        return Gson().fromJson(selctionIds, listType)
    }

}

data class SelectionIds(
    @SerializedName("id")   val id: String,
    @SerializedName("selectionid")  val selectionid: Long,
    @SerializedName("runnerName") val runnerName: String,
    @SerializedName("marketid") val marketid: String
)

data class PreMatchMarket(
    @SerializedName("id") val id: String,
    @SerializedName("fancyid") val fancyId: String,
    @SerializedName("name") val name: String,
    @SerializedName("sportId") val sportId: Int,
    @SerializedName("matchId") val matchId: String,
    @SerializedName("matchname") val matchName: String,
    @SerializedName("seriesId") val seriesId: String,
    @SerializedName("seriesname") val seriesName: String,
    @SerializedName("status") val status: Int,
    @SerializedName("fancyStatus") val fancyStatus: Int,
    @SerializedName("provider") val provider: String,
    @SerializedName("mtype") val mType: String,
    @SerializedName("btype") val bType: String,
    @SerializedName("runnerid") val runnerId: String,
    @SerializedName("createdon") val createdOn: String,
    @SerializedName("createdLong") val createdLong: Long,
    @SerializedName("pSelection") val pSelection: String,
    @SerializedName("fancySource") val fancySource: String,
    @SerializedName("isExtra") val isExtra: Boolean
){
    fun getPSelectionList(): List<PSelection> {
        val listType2 = object : TypeToken<List<PSelection>>() {}.type
        return Gson().fromJson(pSelection, listType2)
    }
}

data class PSelection(
    @SerializedName("eventId") val eventId: String,
    @SerializedName("selectionName") val selectionName: String,
    @SerializedName("id") val id: String
)