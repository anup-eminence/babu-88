package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class GameListResponse(
    @SerializedName("Providers") val providers: List<String>,
    @SerializedName("all_images") val allImages: List<GameImage>
)

data class GameImage(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("game_code") val gameCode: String,
    @SerializedName("image_fav_game") val imageFavGame: Boolean,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("image") val image: String,
    @SerializedName("provider_id") val providerId: String,
    @SerializedName("category_id") val categoryId: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("__v") val version: Double,
    var isStaticImage: Boolean? = false,
    var staticImage: Int? = null
)
