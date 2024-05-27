package com.sona.babu88.api.model.request

import com.google.gson.annotations.SerializedName

data class PromotionListRequest(
    // {"pageNo":1,"timeStamp":"1716786214689","secretKey":"Cei6l0O6QJ"}
    @SerializedName("pageNo")
    val pageNo : Int,
    @SerializedName("timeStamp")
    val timestamp : String?,
    @SerializedName("secretKey")
    val secretKey : String?
)