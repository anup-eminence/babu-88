package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class PlatFormListResponse(
    @SerializedName("Providers") val providers: List<Provider>?
)

data class Provider(
    @SerializedName("provider_name") val providerName: String?,
    @SerializedName("company") val company: String?,
    @SerializedName("provider_id") val providerId: String?,
    @SerializedName("types") val types: List<Type>?
)

data class Type(
    @SerializedName("type_id") val typeId: String?,
    @SerializedName("type_name") val typeName: String?
)