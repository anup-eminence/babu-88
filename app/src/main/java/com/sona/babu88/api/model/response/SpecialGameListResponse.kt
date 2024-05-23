package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class SpecialGameListResponse(
    @SerializedName("MatchImages")
    val matchImages: List<MatchImage>,

    @SerializedName("CasinoImages")
    val casinoImages: List<CasinoImage>
)

data class MatchImage(
    @SerializedName("ImageOrder")
    val imageOrder: String,

    @SerializedName("Image")
    val image: String
)

data class CasinoImage(
    @SerializedName("ImageOrder")
    val imageOrder: String,

    @SerializedName("Image")
    val image: String,

    @SerializedName("image_name")
    val imageName: String,

    @SerializedName("image_url")
    val imageUrl: String
)
